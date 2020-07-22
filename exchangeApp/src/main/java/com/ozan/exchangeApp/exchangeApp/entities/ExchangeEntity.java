package com.ozan.exchangeApp.exchangeApp.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_of_exchange")
@Data
public class ExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "source_currency")
    private String sourceCurrency;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "converted_amount")
    private Double convertedAmount;

    @Column(name = "target_symbol")
    private Character targetSymbol;

    @Column(name = "converted_at")
    private LocalDateTime convertedAt;

}
