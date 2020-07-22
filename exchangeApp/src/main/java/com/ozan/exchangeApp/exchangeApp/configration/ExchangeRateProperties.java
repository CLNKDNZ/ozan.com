package com.ozan.exchangeApp.exchangeApp.configration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "fixer-io-exchange-rate")
@Component
public class ExchangeRateProperties {

    private String url;

}
