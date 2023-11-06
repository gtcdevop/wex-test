package com.gustavo.pro.currency.service;

import com.gustavo.pro.currency.service.conversion.CurrencyApiQuery;
import com.gustavo.pro.currency.service.conversion.model.CurrencyConversionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyConversionService {

    @Autowired
    CurrencyApiQuery currencyService;

    public List<CurrencyConversionModel.CurrencyItem> getCurrencyFromStore() {
        // try to fetch data from database, if not present query the api
        return currencyService.getCurrencyListByDate();
    }
}
