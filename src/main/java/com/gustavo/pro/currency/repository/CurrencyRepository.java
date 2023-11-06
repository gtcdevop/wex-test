package com.gustavo.pro.currency.repository;

import com.gustavo.pro.currency.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

}
