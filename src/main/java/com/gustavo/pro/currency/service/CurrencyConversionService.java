package com.gustavo.pro.currency.service;

import com.gustavo.pro.currency.entity.CurrencyEntity;
import com.gustavo.pro.currency.exceptions.CountryNotFoundException;
import com.gustavo.pro.currency.repository.CurrencyRepository;
import com.gustavo.pro.currency.service.conversion.CurrencyApiQuery;
import com.gustavo.pro.currency.service.conversion.model.CurrencyConversionApiQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
            List<CurrencyConversionApiQueryModel.CurrencyItem> currencyList = currencyService.getCurrencyListByDate(date);

            currencyList.stream().forEach(each -> {
                CurrencyEntity ce = new CurrencyEntity();
                ce.setCountry(each.getCountry());
                ce.setCurrencyName(each.getCurrencyName());
                ce.setRate(each.getExchangeRate());
                ce.setDate(each.getRecordDate());
                this.currencyRepository.save(ce);
            });
        }
        currency = this.currencyRepository.findByDateAndAndCountry(date, country);
        if(this.currencyRepository.countByDate(date) > 0 && currency== null) {
            throw new CountryNotFoundException();
        }
        return currency;
    }




}
