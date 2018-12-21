package com.in28minutes.microservices.currencyconversionservice.controller;

import com.in28minutes.microservices.currencyconversionservice.client.CurrencyExchangeClient;
import com.in28minutes.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.in28minutes.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private static Logger log = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy; // using Feign with Ribbon and Eureka for client side load balancing

    @Autowired
    private CurrencyExchangeClient currencyExchangeClient; // using Ribbon with Eureka for client side load balancing

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){
       return currencyExchangeClient.convertCurrency(from,to,quantity);
    }


    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                       @PathVariable String to,
                                                       @PathVariable BigDecimal quantity){

        CurrencyConversionBean response = currencyExchangeServiceProxy.retrieveExchangeValue(from,to);

        return new CurrencyConversionBean(response.getId(),from,to, response.getConversionMultiple(),
                quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

    @GetMapping("/")
    public String sayHelloToUser(){
        log.info("Inside the CurrencyConversionController - Hi Michael");
        return "Hi Michael";
    }
}
