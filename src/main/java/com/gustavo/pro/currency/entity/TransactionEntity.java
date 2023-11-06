package com.gustavo.pro.currency.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be empty")
    @JsonProperty("name")
    public String name;
}
