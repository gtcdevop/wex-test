package com.gustavo.pro.currency.exceptions.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude
public class ResponseErrorModel {

    private HttpStatus statusCode;
    private String errorMessage;

}
