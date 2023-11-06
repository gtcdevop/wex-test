package com.gustavo.pro.currency.repository;

import com.gustavo.pro.currency.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
