package com.example.co2Automatic.DataManipulationHelpers.XmlAdapters;

import com.example.co2Automatic.models.SessionModels.MoneyCurrency;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class MoneyCurrencyXmlAdapter extends XmlAdapter<String,MoneyCurrency> {

    @Override
    public MoneyCurrency unmarshal(String s) throws Exception {
        return MoneyCurrency.valueOf(s);
    }

    @Override
    public String marshal(MoneyCurrency moneyCurrency) throws Exception {
        return moneyCurrency.name();
    }
}
