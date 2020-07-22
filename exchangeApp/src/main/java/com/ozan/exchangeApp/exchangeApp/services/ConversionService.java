package com.ozan.exchangeApp.exchangeApp.services;

import com.ozan.exchangeApp.exchangeApp.entities.ExchangeEntity;
import com.ozan.exchangeApp.exchangeApp.exception.ConvertExchangeException;
import com.ozan.exchangeApp.exchangeApp.models.ConvertExchangeRequestModel;

public interface ConversionService {

    void convertExchange(ConvertExchangeRequestModel request,String userAgent) throws  ConvertExchangeException;

}
