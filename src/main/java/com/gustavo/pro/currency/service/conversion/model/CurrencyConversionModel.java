package com.gustavo.pro.currency.service.conversion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class CurrencyConversionModel {
    ArrayList<CurrencyItem> data;

    CurrencyConversionModel() {}

    public static class CurrencyItem {

        CurrencyItem() {}

        @JsonProperty("record_date")
        private Date recordDate;
        @JsonProperty
        private String country;
        @JsonProperty("currency")
        private String currencyName;
        @JsonProperty("exchange_rate")
        private Double exchangeRate;

    }
}


