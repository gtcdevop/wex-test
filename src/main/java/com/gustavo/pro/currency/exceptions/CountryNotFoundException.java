package com.gustavo.pro.currency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found exception")
public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException() {
        super();
    }
}
