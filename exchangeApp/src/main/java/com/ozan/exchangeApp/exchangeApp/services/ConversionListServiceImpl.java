package com.ozan.exchangeApp.exchangeApp.services;

import com.ozan.exchangeApp.exchangeApp.entities.ExchangeEntity;

import com.ozan.exchangeApp.exchangeApp.repository.ConversionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class ConversionListServiceImpl implements ConversionListService {

    private static final Logger logger = LoggerFactory.getLogger(ConversionListService.class);

    private final ConversionRepository conversionRepository;

    @Override
    public Page<ExchangeEntity> getAllConvertedExchange(Pageable pageable) {

        return conversionRepository.findAll(pageable);

    }
}
