package com.gustavo.pro.currency.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "currency")
@Data
public class CurrencyEntity implements Serializable {

    private static final long serialVersionUID = 312321412L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    private Date date;

    private String currencyName;

    private Double rate;

    private String country;

    CurrencyEntity(Date date, String currencyName, Double rate, String country) {
        this.date = date;
        this.currencyName = currencyName;
        this.rate = rate;
        this.country = country;
    }

    public CurrencyEntity() {

    }
}
