package com.gustavo.pro.currency.controller;

import com.gustavo.pro.currency.entity.TransactionEntity;
import com.gustavo.pro.currency.service.CurrencyConversionService;
import com.gustavo.pro.currency.service.conversion.model.CurrencyConversionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    CurrencyConversionService currencyConversionService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody TransactionEntity transaction) {
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionByID(@PathVariable Long id) {
        List<CurrencyConversionModel.CurrencyItem> currencyList =
                this.currencyConversionService.getCurrencyFromStore();
        return ResponseEntity.ok(currencyList);
    }

}
