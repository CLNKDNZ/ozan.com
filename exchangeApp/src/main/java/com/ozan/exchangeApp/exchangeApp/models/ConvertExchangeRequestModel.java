package com.ozan.exchangeApp.exchangeApp.models;

import com.ozan.exchangeApp.exchangeApp.enums.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConvertExchangeRequestModel {

    @NotNull
    private Currency sourceCurrency;

    @NotNull
    private Currency targetCurrency;

    @NotNull
    private Double sourceAmount;

}
