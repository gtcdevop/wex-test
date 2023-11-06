package com.gustavo.pro.currency.service;

import com.gustavo.pro.currency.entity.TransactionEntity;
import com.gustavo.pro.currency.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<?> checkValidTransactionSaveInDatabase(TransactionEntity transaction) {
        TransactionEntity te = this.transactionRepository.save(transaction);
        return ResponseEntity.ok(te);
    }

    public TransactionEntity getTransactionFromId(long transactionId) {
        TransactionEntity te = this.transactionRepository.getById(transactionId);
        return te;
    }
}
