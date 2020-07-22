package com.ozan.exchangeApp.exchangeApp.services;

import com.ozan.exchangeApp.exchangeApp.enums.Currency;
import com.ozan.exchangeApp.exchangeApp.exception.GetRateByRestClientException;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeRateService {

    Double getRateByRestClient(Currency sourceCurrency, Currency targetCurrency,String userAgent) throws GetRateByRestClientException;
}
