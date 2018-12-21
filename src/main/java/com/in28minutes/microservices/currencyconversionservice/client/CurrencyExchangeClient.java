package com.in28minutes.microservices.currencyconversionservice.client;

import com.in28minutes.microservices.currencyconversionservice.configuration.RibbonClientLoadBalanceConfiguration;
import com.in28minutes.microservices.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@RibbonClient(name = "currency-exchange-service", configuration = RibbonClientLoadBalanceConfiguration.class)
public class CurrencyExchangeClient {

    @Autowired
    RestTemplate restTemplate;

    public CurrencyConversionBean convertCurrency(String from,String to,BigDecimal quantity){
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity("http://currency-exchange-service/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();

        return new CurrencyConversionBean(response.getId(),from,to, response.getConversionMultiple(),
                quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }
}
