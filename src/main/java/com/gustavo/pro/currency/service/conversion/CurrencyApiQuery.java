package com.gustavo.pro.currency.service.conversion;

import com.gustavo.pro.currency.service.conversion.model.CurrencyConversionApiQueryModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyApiQuery {
    private final RestTemplate restTemplate = new RestTemplate();


    private String getMonthFromLocalDate(LocalDate ld) {
        int month = ld.getMonthValue();
        if(month<10) {
            return "0"+month;
        } else {
            return ""+month;
        }
    }

    public List<CurrencyConversionApiQueryModel.CurrencyItem> getCurrencyListByDate(LocalDate date) {
        String currencyApiUrl = new StringBuilder()
            .append("https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange")
            .append("?filter=record_date:eq:")
            .append(date.getYear()).append("-").append(getMonthFromLocalDate(date)).append("-") .append(date.getDayOfMonth())
            .append("&fields=record_date,country,currency,exchange_rate")
            .append("&sort=country")
            .append("&page[number]=")
            .toString();

        List<CurrencyConversionApiQueryModel.CurrencyItem> result = Arrays.asList(1, 2).parallelStream().map(page -> {
            ResponseEntity<CurrencyConversionApiQueryModel> response =
                    restTemplate.getForEntity(currencyApiUrl + page, CurrencyConversionApiQueryModel.class);
            CurrencyConversionApiQueryModel ccModel = response.getBody();
            return ccModel.getData();
        }).flatMap(List::stream).collect(Collectors.toList());

        return result;
    }
}
