package com.gustavo.pro.currency.controller;

import com.gustavo.pro.currency.entity.TransactionEntity;
import com.gustavo.pro.currency.service.CurrencyConversionService;
import com.gustavo.pro.currency.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    CurrencyConversionService currencyConversionService;

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionEntity transaction) {
        try {
            return ResponseEntity.ok(transactionService.checkValidTransactionSaveInDatabase(transaction));
        } catch (DataIntegrityViolationException e){
            String message = new StringBuilder().append("{\"error\":\"")
                    .append(
                            e.getRootCause().getMessage()
                                    .replace("\"","'")
                                    .replace("\n","")
                    )
                    .append("\"}")
                    .toString();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionByID(@PathVariable Long id) {
        try {
            TransactionEntity te = this.transactionService.getTransactionFromId(id);
            return ResponseEntity.ok(te);
        } catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/{id}/{countryCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTransactionByIdAndConvertedCurrency(
            @PathVariable Long id, @PathVariable String countryCode) {

        return this.transactionService.getTransactionByIdAndCountryAndMakeConversion(id, countryCode);

    }
}
