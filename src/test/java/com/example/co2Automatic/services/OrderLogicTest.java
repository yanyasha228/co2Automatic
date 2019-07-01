package com.example.co2Automatic.services;

import com.example.co2Automatic.HelpUtils.CustomExceptions.ImpossibleSettingException;
import com.example.co2Automatic.HelpUtils.CustomExceptions.InsufficientAmountException;
import com.example.co2Automatic.models.*;
import com.example.co2Automatic.models.ModelEnums.ClientStatus;
import com.example.co2Automatic.models.ModelEnums.MoneyCurrency;
import com.example.co2Automatic.models.ModelEnums.PaymentMethod;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderLogicTest {


    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    private static Order ord;

    private long prodTestId;

    private long orderTestId;

    private long clientTestId;

    public static class OrderEx extends Order{

        public List<OrderLine> orderLinesPO;

        public OrderEx(){
            super();
            orderLinesPO = getOrderLines();

        }


    }

    @Before
    public void addTestData() throws ImpossibleSettingException, InsufficientAmountException {
        Product newProd = new Product();
        newProd.setName("ProdName");
        newProd.setCurrency(MoneyCurrency.UAH);
        newProd.setCountryOfOrigin("USA");
        newProd.setDescription("ProdDescription");
        newProd.setQuantity(10);

        Order newOrder = new Order();
        newOrder.setDeliveryPlace("Kiev");
        newOrder.setDeliveryDate(LocalDate.now());
        newOrder.setDeliveryPlaceWarehouseNumber(78);
        newOrder.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
        newOrder.setOrderComment("That seems good");

        Client orderClient = new Client();
        orderClient.setEmail("igor777@gmail.com");
        orderClient.setPhoneNumber("380660001507");
        orderClient.setName("Igor");
        orderClient.setLastName("Seleverstov");
        orderClient.setMiddleName("Vasilevich");
        orderClient.setUsualDeliveryPlace("Kiev");
        orderClient.setUsualWarehouseNumber(25);
        orderClient.setClientStatus(ClientStatus.USUAL);

        orderClient.getClientsOrders().add(newOrder);

        newOrder.setClient(orderClient);


        orderTestId = orderService.save(newOrder).getId();
        clientTestId = clientService.save(orderClient).getId();

        prodTestId = productService.save(newProd).getId();
    }


    @Test(expected = InsufficientAmountException.class)
    public void setProductToOrderLineWithInsufficientAmount() throws InsufficientAmountException {
        OrderLine newOrdLine = new OrderLine();
        newOrdLine.setProductWithAmount(productService.findById(prodTestId).orElse(null), 11);
    }

    @Test(expected = ImpossibleSettingException.class)
    @Transactional
    public void setOrderLinesIntoNonEmptyOrderLinesSetInOrder() throws ImpossibleSettingException, InsufficientAmountException {
        OrderLine newOrdLine = new OrderLine();
        Set<OrderLine> orderLinesSet = new HashSet<>();
        Order orderFromDb = orderService.findById(orderTestId).get();

        newOrdLine.setProductWithAmount(productService.findById(prodTestId).get(), 2);

        orderLinesSet.add(newOrdLine);

        orderFromDb.setOrderLines(orderLinesSet);

        orderService.save(orderFromDb);

        Assert.assertEquals(8, productService.findById(prodTestId).get().getQuantity());

        orderFromDb = orderService.findById(orderTestId).get();
        Set<OrderLine> orderLinesSet2 = new HashSet<>();
        OrderLine newOrdLine2 = new OrderLine();
        newOrdLine2.setProductWithAmount(productService.findById(prodTestId).get(), 3);

        orderLinesSet2.add(newOrdLine2);

        //Must throw ImpossibleSettingException
        orderFromDb.setOrderLines(orderLinesSet2);

        orderService.save(orderFromDb);

        List<OrderLine> orderLinesFromDb = orderLineService.findAll();

        Assert.assertEquals(7, productService.findById(prodTestId).get().getQuantity());
    }

    @Test
    @Transactional
    public void setOrderLineAndCheckReservedAmountInProduct() throws InsufficientAmountException, ImpossibleSettingException {
        OrderLine newOrdLine = new OrderLine();
        Set<OrderLine> orderLinesSet = new HashSet<>();
        Order orderFromDb = orderService.findById(orderTestId).get();

        newOrdLine.setProductWithAmount(productService.findById(prodTestId).get(), 2);

        orderLinesSet.add(newOrdLine);

        orderFromDb.setOrderLines(orderLinesSet);

        orderService.save(orderFromDb);

        Assert.assertEquals(8, productService.findById(prodTestId).get().getQuantity());

        orderService.delete(orderTestId);

        Assert.assertEquals(10, productService.findById(prodTestId).get().getQuantity());

    }

    @Test
    @Transactional
    public void setOrderLineAndCheckReservedAmountInProductAfterOrderLinesReplacement() throws InsufficientAmountException, ImpossibleSettingException {
        OrderLine newOrdLine = new OrderLine();
        Set<OrderLine> orderLinesSet = new HashSet<>();
        Order orderFromDb = orderService.findById(orderTestId).get();

        newOrdLine.setProductWithAmount(productService.findById(prodTestId).get(), 2);

        orderLinesSet.add(newOrdLine);

        orderFromDb.setOrderLines(orderLinesSet);

        orderService.save(orderFromDb);

        Assert.assertEquals(8, productService.findById(prodTestId).get().getQuantity());

        orderFromDb = orderService.findById(orderTestId).get();
        Set<OrderLine> orderLinesSet2 = new HashSet<>();
        OrderLine newOrdLine2 = new OrderLine();
        newOrdLine2.setProductWithAmount(productService.findById(prodTestId).get(), 3);

        orderLinesSet2.add(newOrdLine2);

        orderLineService.delete(orderFromDb.getOrderLinesImmutable());

        orderFromDb.setOrderLines(orderLinesSet2);

        orderService.save(orderFromDb);

        List<OrderLine> orderLinesFromDb = orderLineService.findAll();

        Assert.assertEquals(7, productService.findById(prodTestId).get().getQuantity());

    }





}
