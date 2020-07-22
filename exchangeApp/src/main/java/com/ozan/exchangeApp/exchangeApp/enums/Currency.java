package com.ozan.exchangeApp.exchangeApp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    TRY('₺'),
    USD('$'),
    EUR('€'),
    GBP('£'),
    JPY('¥'),
    RUB('₽'),
    AUD('$');

    private Character currencySymbol;

}
