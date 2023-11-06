package com.gustavo.pro.currency.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transaction")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "transactionDate cannot be empty")
    @JsonProperty("transaction_date")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate transacionDate;

    @Column(length = 50)
    @Size(max=50)
    @JsonProperty("description")
    public String description;

    @JsonProperty("amount")
    public double amount;
}
