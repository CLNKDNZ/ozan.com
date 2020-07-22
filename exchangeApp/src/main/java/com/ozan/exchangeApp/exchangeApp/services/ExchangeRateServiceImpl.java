package com.ozan.exchangeApp.exchangeApp.services;

import com.ozan.exchangeApp.exchangeApp.configration.ExchangeRateProperties;
import com.ozan.exchangeApp.exchangeApp.enums.Currency;
import com.ozan.exchangeApp.exchangeApp.exception.GetRateByRestClientException;
import com.ozan.exchangeApp.exchangeApp.models.ConverterResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    private final ExchangeRateProperties exchangeRateProperties;

    @Override
    public Double getRateByRestClient(Currency sourceCurrency, Currency targetCurrency, String userAgent) throws GetRateByRestClientException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", userAgent);

            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ConverterResponse rsponse = restTemplate.exchange(
                    String.format("%sbase=%s&symbols=%s", exchangeRateProperties.getUrl(), sourceCurrency, targetCurrency), HttpMethod.GET, entity,
                    ConverterResponse.class).getBody();

            return Double.parseDouble(rsponse.getRates().get(targetCurrency.name()));
        }
        catch (Exception e) {
            throw new GetRateByRestClientException("asdadad");
        }

    }
}
