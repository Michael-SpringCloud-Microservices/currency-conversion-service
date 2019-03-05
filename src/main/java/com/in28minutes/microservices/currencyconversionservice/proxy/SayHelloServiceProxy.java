package com.in28minutes.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="say-hello-service")
@RibbonClient(name="say-hello-service")
public interface SayHelloServiceProxy {
    @GetMapping("/")
    public String sayHelloToUser();

}
