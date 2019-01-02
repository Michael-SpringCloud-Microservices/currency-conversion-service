package com.in28minutes.microservices.currencyconversionservice.client;

import com.in28minutes.microservices.currencyconversionservice.configuration.RibbonClientLoadBalanceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RibbonClient(name = "say-hello-service", configuration = RibbonClientLoadBalanceConfiguration.class)
public class SayHelloClient {
    private static Logger log = LoggerFactory.getLogger(SayHelloClient.class);

    @Autowired
    RestTemplate restTemplate;

    public String getHelloString(){
        String response = restTemplate.getForObject("http://say-hello-service/",String.class);
        log.info("The hello string received >>> " + response);
        return response;
    }
}
