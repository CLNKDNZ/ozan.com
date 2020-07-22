package com.ozan.exchangeApp.exchangeApp.resources;

import com.ozan.exchangeApp.exchangeApp.entities.ExchangeEntity;
import com.ozan.exchangeApp.exchangeApp.exception.ConvertExchangeException;
import com.ozan.exchangeApp.exchangeApp.models.ConvertExchangeRequestModel;
import com.ozan.exchangeApp.exchangeApp.services.ConversionListService;
import com.ozan.exchangeApp.exchangeApp.services.ConversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@Api(value = "ozan.com Exchange API", description = "Include exchange conversion procceses")
@RequestMapping("/exchange")
@RequiredArgsConstructor
@Data
public class ExchangeAPIResource implements Serializable {

    private final ConversionService conversionService;

    private final ConversionListService conversionListService;

    @Value("${page-size}")
    private Integer pageSize;

    @PostMapping(value = "/convert")
    @ApiOperation(value = "Conversion of exchange resource", response = ResponseEntity.class, responseContainer = "ApiResponse")
    public ResponseEntity<String> convertExchange(@RequestHeader(value = "User-Agent") String userAgent,
                                                  @RequestBody @Valid ConvertExchangeRequestModel request) throws ConvertExchangeException {
        conversionService.convertExchange(request, userAgent);
        return new ResponseEntity("Succes", HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/{page}")
    @ApiOperation(value = "Get all converted ", response = ResponseEntity.class, responseContainer = "ApiResponse")
    public ResponseEntity<String> getAllConvertedExchange(@PathVariable("page") Integer page) {
        Pageable paging = PageRequest.of(page, pageSize);
        Page<ExchangeEntity> pagedData = conversionListService.getAllConvertedExchange(paging);
        return new ResponseEntity(pagedData, HttpStatus.OK);
    }
}
