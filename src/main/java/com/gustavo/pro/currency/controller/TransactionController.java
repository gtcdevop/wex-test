package com.gustavo.pro.currency.controller;

import com.gustavo.pro.currency.entity.TransactionEntity;
import com.gustavo.pro.currency.service.CurrencyConversionService;
import com.gustavo.pro.currency.service.TransactionService;
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

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionEntity transaction) {
        return transactionService.checkValidTransactionSaveInDatabase(transaction);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionByID(@PathVariable Long id) {
        return ResponseEntity.ok(this.transactionService.getTransactionFromId(id));
    }


    @GetMapping(value = "/{id}/{countryCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionByIdAndConvertedCurrency(
            @PathVariable Long id, @PathVariable String countryCode) {
        return ResponseEntity.ok("");
    }
}
