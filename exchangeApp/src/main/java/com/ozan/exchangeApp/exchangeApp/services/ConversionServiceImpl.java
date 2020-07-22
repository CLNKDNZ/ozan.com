package com.ozan.exchangeApp.exchangeApp.services;

import com.ozan.exchangeApp.exchangeApp.configration.ExchangeRateProperties;
import com.ozan.exchangeApp.exchangeApp.entities.ExchangeEntity;
import com.ozan.exchangeApp.exchangeApp.exception.BindExchangeEntityException;
import com.ozan.exchangeApp.exchangeApp.exception.ConvertExchangeException;

import com.ozan.exchangeApp.exchangeApp.exception.GetRateByRestClientException;
import com.ozan.exchangeApp.exchangeApp.models.ConvertExchangeRequestModel;
import com.ozan.exchangeApp.exchangeApp.repository.ConversionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class ConversionServiceImpl implements ConversionService {

    private final ExchangeRateService exchangeRateService;

    private final ConversionRepository conversionRepository;

    private final ExchangeRateProperties exchangeRateProperties;

    @Override
    @Transactional
    public void convertExchange(ConvertExchangeRequestModel request, String userAgent) throws ConvertExchangeException {
        try {
            Double rate = exchangeRateService.getRateByRestClient(request.getSourceCurrency(), request.getTargetCurrency(), userAgent);
            ExchangeEntity exchangeEntity = bindExchangeEntity(request, rate);
            conversionRepository.save(exchangeEntity);
            log.info("{} converted to {} by {} rate", request.getSourceCurrency(), request.getTargetCurrency(), rate);
        }
        catch (GetRateByRestClientException | BindExchangeEntityException ex) {
            log.error("An error occured when converting exchange" + ex.getCause());
            throw new ConvertExchangeException("An error occured when converting exchange : " + ex.getCause());

        }

    }

    private ExchangeEntity bindExchangeEntity(ConvertExchangeRequestModel request, Double rate) throws BindExchangeEntityException {
        try {
            ExchangeEntity exchangeEntity = new ExchangeEntity();
            exchangeEntity.setConvertedAmount(rate * request.getSourceAmount());
            exchangeEntity.setConvertedAt(LocalDateTime.now());
            exchangeEntity.setRate(rate);
            exchangeEntity.setSourceCurrency(request.getSourceCurrency().name());
            exchangeEntity.setTargetCurrency(request.getTargetCurrency().name());
            exchangeEntity.setTargetSymbol(request.getTargetCurrency().getCurrencySymbol());
            return exchangeEntity;
        }
        catch (Exception e) {

            throw new BindExchangeEntityException("An error occured when binding entity");

        }

    }

}
