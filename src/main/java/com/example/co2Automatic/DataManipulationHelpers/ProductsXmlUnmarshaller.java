package com.example.co2Automatic.DataManipulationHelpers;

import com.example.co2Automatic.models.Product;
import com.example.co2Automatic.models.ProductCategory;
import com.example.co2Automatic.models.MoneyCurrency;
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
        Node currentXmlOffer;
        NodeList xmlOffer;
        Node currentXmlOfferParam;
        String paramTextContent;
        Node category;
        NamedNodeMap categoriesAttributes;
        Node categoriesId;
        Node categoriesParentId;
        NamedNodeMap paramAttr;
        Node nameParam;

        for (int i = 0; i < xmlOfferList.getLength(); i++) {
            Product product = new Product();
            currentXmlOffer = xmlOfferList.item(i);
            xmlOffer = currentXmlOffer.getChildNodes();
            for (int j = 0; j < xmlOffer.getLength(); j++) {
                currentXmlOfferParam = xmlOffer.item(j);
                paramTextContent = currentXmlOfferParam.getTextContent();
                switch (currentXmlOfferParam.getNodeName().toLowerCase()) {
                    case "param":
                        paramAttr = currentXmlOfferParam.getAttributes();
                        nameParam = paramAttr.getNamedItem("name");
                        product.getParams().put(nameParam.getNodeValue(), paramTextContent);
                        break;

                    case "picture":
                        product.getImageUrls().add(paramTextContent);
                        break;

                    case "url":
                        product.setProductUrlFromExternalResource(paramTextContent);
                        break;
                    case "price":
                        product.setPrice(Double.valueOf(paramTextContent));
                        break;
                    case "currencyid":
                        switch (paramTextContent) {
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
                    case "categoryid":
                        product.setCategoryXmlId(Integer.valueOf(paramTextContent));
                        for (int c = 0; c < categoriesList.getLength(); c++) {
                            category = categoriesList.item(c);
                            categoriesAttributes = category.getAttributes();
                            categoriesId = categoriesAttributes.getNamedItem("id");
                            if (categoriesId.getNodeValue().equalsIgnoreCase(paramTextContent)) {
                                categoriesParentId = categoriesAttributes.getNamedItem("parentId");
                                if (categoriesParentId != null) {
                                    product.setCategoryXmlId(Integer.valueOf(categoriesParentId.getNodeValue()));
                                }

                            }
                        }
                        break;
                    case "country_of_origin":
                        product.setCountryOfOrigin(paramTextContent);
                    case "description":
                        product.setDescription(paramTextContent);
                        break;
                    case "name":
                        product.setName(paramTextContent);
                        break;
                    case "vendor":
                        product.setVendor(paramTextContent);
                        break;

                }

            }
            unmarshalledProductList.add(product);
        }

        return unmarshalledProductList;
    }

}
