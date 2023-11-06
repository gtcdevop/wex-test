package com.gustavo.pro.currency.service.conversion;

import com.gustavo.pro.currency.service.conversion.model.CurrencyConversionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CurrencyApiQuery {
    private final RestTemplate restTemplate = new RestTemplate();



    public List<CurrencyConversionModel.CurrencyItem> getCurrencyListByDate() {
        String currencyApiUrl = new StringBuilder()
                .append("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange")
                .append("?filter=record_date:eq:2021-09-30")
                .append("&fields=record_date,country,currency,exchange_rate")
                .append("&sort=country")
                .append("&page[number]=")
                .toString();

        List<CurrencyConversionModel.CurrencyItem> result = Arrays.asList(1, 2).parallelStream().map(page -> {
            ResponseEntity<CurrencyConversionModel> response =
                    restTemplate.getForEntity(currencyApiUrl + page, CurrencyConversionModel.class);
            CurrencyConversionModel ccModel = response.getBody();
            return ccModel.getData();
        }).flatMap(List::stream).collect(Collectors.toList());

        return result;
    }
}
