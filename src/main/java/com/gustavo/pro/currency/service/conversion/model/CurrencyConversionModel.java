package com.gustavo.pro.currency.service.conversion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class CurrencyConversionModel {
    ArrayList<CurrencyItem> data;

    CurrencyConversionModel() {}

    @Data
    public static class CurrencyItem {

        CurrencyItem() {}

        @JsonProperty("record_date")
        private LocalDate recordDate;
        @JsonProperty
        private String country;
        @JsonProperty("currency")
        private String currencyName;
        @JsonProperty("exchange_rate")
        private Double exchangeRate;

    }
}


