package com.ozan.exchangeApp.exchangeApp.services;

import com.ozan.exchangeApp.exchangeApp.entities.ExchangeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ConversionListService {

    Page<ExchangeEntity> getAllConvertedExchange(Pageable pageable);
}
