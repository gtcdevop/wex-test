package com.gustavo.pro.currency.service;

import com.gustavo.pro.currency.entity.CurrencyEntity;
import com.gustavo.pro.currency.entity.TransactionEntity;
import com.gustavo.pro.currency.exceptions.CurrencyNotExchangebleException;
import com.gustavo.pro.currency.exceptions.NotFoundException;
import com.gustavo.pro.currency.repository.TransactionRepository;
import com.gustavo.pro.currency.service.conversion.model.TransactionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CurrencyConversionService currencyConversionService;

    public TransactionEntity checkValidTransactionSaveInDatabase(TransactionEntity transaction) {
        TransactionEntity te = this.transactionRepository.save(transaction);
        return te;
    }

    public TransactionEntity getTransactionFromId(long transactionId) {
        Optional<TransactionEntity> te = this.transactionRepository.findById(transactionId);
        try {
            return te.get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }
    }

    public ResponseEntity<?> getTransactionByIdAndCountryAndMakeConversion(long transactionId, String country) {
        TransactionEntity te = this.getTransactionFromId(transactionId);
        if (te == null) {
            return ResponseEntity.notFound().build();
        } else {
            CurrencyEntity currency = null;
            int dateToSubtract = 0;
            LocalDate dateToQuery = te.getTransacionDate();
            do {
                currency= this.currencyConversionService.getCurrencyExchangeRateByCountryAndByDate(
                        country,dateToQuery);
                dateToSubtract++;
                dateToQuery = dateToQuery.minusDays(dateToSubtract);
            } while(currency == null && dateToSubtract < 183 );
            // build the response
            if(currency == null ) {
                throw new CurrencyNotExchangebleException();
            }
            return ResponseEntity.ok(generateTransactionResponseEntity(currency, te));
        }
    }

    private TransactionResponseModel generateTransactionResponseEntity(CurrencyEntity currencyEntity, TransactionEntity te) {
        TransactionResponseModel trm = new TransactionResponseModel();
        trm.setAmount(te.getAmount());
        trm.setCountry(currencyEntity.getCountry());
        trm.setConvertedValue(
                BigDecimal.valueOf(
                        te.getAmount()*currencyEntity.getRate()
                ).setScale(2, RoundingMode.HALF_UP)
        );
        trm.setDescription(te.getDescription());
        trm.setTransacionDate(te.getTransacionDate());
        trm.setExchangeDate(currencyEntity.getDate());
        trm.setId(te.getId());
        return trm;
    }


}
