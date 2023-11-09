package com.gustavo.pro.currency.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Exchange rate not found in 6 months")
public class CurrencyNotExchangebleException extends RuntimeException{
    CurrencyNotExchangebleException() {
        super();
    }
}
