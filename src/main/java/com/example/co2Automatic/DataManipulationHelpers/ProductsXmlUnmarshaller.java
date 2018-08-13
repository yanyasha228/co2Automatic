package com.example.co2Automatic.DataManipulationHelpers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.SessionModels.MoneyCurrency;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ProductsXmlUnmarshaller {


    private String xmlStringToUnmarshalling;
    private Document cDoc;

    public ProductsXmlUnmarshaller(String xmlStringToUnmarshalling) {
        this.xmlStringToUnmarshalling = xmlStringToUnmarshalling;
    }

    public ProductsXmlUnmarshaller buildXml() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            cDoc = documentBuilder.parse(new InputSource(new StringReader(xmlStringToUnmarshalling)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public List<ProductCategory> getCategoriesFromXml() {

        Element rootElement = cDoc.getDocumentElement();

        NodeList categoriesList = rootElement.getElementsByTagName("category");

        List<ProductCategory> productCategoryList = new ArrayList<>();
        for (int i = 0; i < categoriesList.getLength(); i++) {
            Node category = categoriesList.item(i);
            NamedNodeMap categoriesAttributes = category.getAttributes();
            Node categoriesId = categoriesAttributes.getNamedItem("id");
            Node categoriesParentId = categoriesAttributes.getNamedItem("parentId");

            if (categoriesParentId == null) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(Integer.valueOf(categoriesId.getNodeValue()));
                productCategory.setName(category.getTextContent());
                productCategoryList.add(productCategory);
            }
        }


        return productCategoryList;

    }


    public List<Product> getProductListFromXml() {
        List<Product> unmarshalledProductList = new ArrayList<>();

        Element rootElement = cDoc.getDocumentElement();
        NodeList categoriesList = rootElement.getElementsByTagName("category");
        NodeList xmlOfferList = rootElement.getElementsByTagName("offer");

        for (int i = 0; i < xmlOfferList.getLength(); i++) {
            Product product = new Product();
            Node currentXmlOffer = xmlOfferList.item(i);
            NodeList xmlOffer = currentXmlOffer.getChildNodes();
            for (int j = 0; j < xmlOffer.getLength(); j++) {
                Node currentXmlOfferParam = xmlOffer.item(j);
                switch (currentXmlOfferParam.getNodeName().toLowerCase()) {
                    case "url":
                        product.setProductUrlFromExternalResource(currentXmlOfferParam.getTextContent());
                        break;
                    case "price":
                        product.setPrice(Double.valueOf(currentXmlOfferParam.getTextContent()));
                        break;
                    case "currencyid":
                        switch (currentXmlOffer.getTextContent()) {
                            case "USD":
                                product.setCurrency(MoneyCurrency.USD);
                                break;
                            case "EUR":
                                product.setCurrency(MoneyCurrency.EUR);
                                break;
                            case "UAH":
                                product.setCurrency(MoneyCurrency.UAH);
                                break;
                        }
                        break;
                    case "description":
                        product.setDescription(currentXmlOfferParam.getTextContent());
                        break;
                    case "picture":
                        product.getImageUrls().add(currentXmlOfferParam.getTextContent());
                        break;
                    case "name":
                        product.setName(currentXmlOfferParam.getTextContent());
                        break;
                    case "vendor":
                        product.setVendor(currentXmlOfferParam.getTextContent());
                        break;
                    case "categoryid":
                        product.setCategorryXmlId(Integer.valueOf(currentXmlOfferParam.getTextContent()));
                        for (int c = 0; c < categoriesList.getLength(); c++) {
                            Node category = categoriesList.item(c);
                            NamedNodeMap categoriesAttributes = category.getAttributes();
                            Node categoriesId = categoriesAttributes.getNamedItem("id");
                            if (categoriesId.getNodeValue().equalsIgnoreCase(currentXmlOfferParam.getTextContent())) {
                                Node categoriesParentId = categoriesAttributes.getNamedItem("parentId");
                                if (categoriesParentId != null) {
                                    product.setCategorryXmlId(Integer.valueOf(categoriesParentId.getNodeValue()));
                                }

                            }
                        }


                }

            }

        }

        return unmarshalledProductList;
    }

}
