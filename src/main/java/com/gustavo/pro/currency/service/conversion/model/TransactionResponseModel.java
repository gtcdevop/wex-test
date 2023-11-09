package com.gustavo.pro.currency.service.conversion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties
@Data
public class TransactionResponseModel  {

    @JsonProperty("id")
    private long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("transaction_date")
    public LocalDate transacionDate;

    @JsonProperty("description")
    public String description;

    @JsonProperty("amount_native_currency")
    public double amount;

    @JsonProperty("country")
    public String country;

    @JsonProperty("amount_converted_dollar")
    public BigDecimal convertedValue;
}
