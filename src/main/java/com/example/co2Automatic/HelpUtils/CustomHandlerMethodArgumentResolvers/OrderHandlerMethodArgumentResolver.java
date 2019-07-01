package com.example.co2Automatic.HelpUtils.CustomHandlerMethodArgumentResolvers;

import com.example.co2Automatic.models.*;
import com.example.co2Automatic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class OrderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    ProductService productService;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Order.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        String id = nativeWebRequest.getParameter("id");
        String clientId = nativeWebRequest.getParameter("clientId");
        String inputEmail = nativeWebRequest.getParameter("inputEmail");
        String inputPhoneNumber = nativeWebRequest.getParameter("inputPhoneNumber");
        String inputDeliveryDate = nativeWebRequest.getParameter("inputDeliveryDate");
        String inputPaymentMethod = nativeWebRequest.getParameter("inputPaymentMethod");
        String inputName = nativeWebRequest.getParameter("inputName");
        String inputLastName = nativeWebRequest.getParameter("inputLastName");
        String inputMiddleName = nativeWebRequest.getParameter("inputMiddleName");
        String inputCity = nativeWebRequest.getParameter("inputCity");
        String inputWarehouseNumber = nativeWebRequest.getParameter("inputWarehouseNumber");
        String inputOrderComment = nativeWebRequest.getParameter("inputOrderComment");
        String[] orderLineProductIdInput = null;
        String[] inputOrderLineProductQua = null;
        String inputWeight = nativeWebRequest.getParameter("inputWeight");

        Map<String, String[]> parameterMap = nativeWebRequest.getParameterMap();

      for(String key : parameterMap.keySet()){

          if(key.equalsIgnoreCase("prodOrderLineProductIdInput")){
              orderLineProductIdInput = parameterMap.get(key);
          }

          if(key.equalsIgnoreCase("inputOrderLineProductQua")){
              inputOrderLineProductQua = parameterMap.get(key);
          }
        }

//        for (int i = 0; i < orderLineProductIdInput.length; i++) {
//
//            OrderLine newOrderLine = new OrderLine();
//            Product prodForOrderLine = productService.findById(Long.valueOf(orderLineProductIdInput[i])).orElse(null);
//            if (prodForOrderLine != null) {
//                newOrderLine.setProductWithAmount(prodForOrderLine, Integer.valueOf(inputOrderLineProductQua[i]));
//
//                if (orderClient.getClientStatus() == ClientStatus.WHOLESALER) {
//                    newOrderLine.setPurchasePrice(newOrderLine.getProduct().getWholeSalePrice() * newOrderLine.getAmount());
//                } else {
//                    newOrderLine.setPurchasePrice(newOrderLine.getProduct().getPrice() * newOrderLine.getAmount());
//                }
//                orderLines.add(newOrderLine);
//
//            }


        Order newOrder = new Order();

        return newOrder;
        }



    private boolean isNotSet(String value) {
        return value == null;
    }
}
