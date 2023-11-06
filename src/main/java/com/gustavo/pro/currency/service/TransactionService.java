package com.gustavo.pro.currency.service;

import com.gustavo.pro.currency.entity.TransactionEntity;
import com.gustavo.pro.currency.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionEntity checkValidTransactionSaveInDatabase(TransactionEntity transaction) {
        TransactionEntity te = this.transactionRepository.save(transaction);
        return te;
    }

    public TransactionEntity getTransactionFromId(long transactionId) {
        Optional<TransactionEntity> te = this.transactionRepository.findById(transactionId);
        return te.get();
    }
}
