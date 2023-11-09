package com.gustavo.pro.currency.repository;

import com.gustavo.pro.currency.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    public CurrencyEntity findByDateAndAndCountry(LocalDate date, String country);

    public Long countByDate(LocalDate date);
}
