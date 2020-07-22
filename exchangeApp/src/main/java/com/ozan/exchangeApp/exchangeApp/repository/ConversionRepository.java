package com.ozan.exchangeApp.exchangeApp.repository;

import com.ozan.exchangeApp.exchangeApp.entities.ExchangeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConversionRepository extends PagingAndSortingRepository<ExchangeEntity, Long> {

    ExchangeEntity save(ExchangeEntity exchangeEntity);

    Page<ExchangeEntity> findAll(Pageable pageable);
}
