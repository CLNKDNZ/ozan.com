package com.ozan.exchangeApp.exchangeApp.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
@Data
public class ConverterResponse {

    String base;

    HashMap<String,String> rates;

    LocalDate date;
}
