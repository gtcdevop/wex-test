package com.gustavo.pro.currency.service;

import com.gustavo.pro.currency.entity.CurrencyEntity;
import com.gustavo.pro.currency.repository.CurrencyRepository;
import com.gustavo.pro.currency.service.conversion.CurrencyApiQuery;
import com.gustavo.pro.currency.service.conversion.model.CurrencyConversionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyConversionService {

    @Autowired
    CurrencyApiQuery currencyService;

    @Autowired
    CurrencyRepository currencyRepository;

    public CurrencyEntity getCurrencyExchangeRateByCountryAndByDate(String country, LocalDate date) {
        // try to fetch data from database, if not present query the api
        CurrencyEntity currency = this.currencyRepository.findByDateAndAndCountry(date, country);
        if(currency == null) {
            currencyService.getCurrencyListByDate(date).stream().forEach(each-> {
                CurrencyEntity ce = new CurrencyEntity();
                ce.setCountry(each.getCountry());
                ce.setCurrencyName(each.getCurrencyName());
                ce.setRate(each.getExchangeRate());
                ce.setDate(each.getRecordDate());
            });
        }
        currency = this.currencyRepository.findByDateAndAndCountry(date, country);
        return currency;
    }




}
