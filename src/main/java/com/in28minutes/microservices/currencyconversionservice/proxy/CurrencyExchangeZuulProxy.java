/**
 * 
 */
package com.in28minutes.microservices.currencyconversionservice.proxy;

/**
 * @author 109726
 *
 */



import com.in28minutes.microservices.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// The following annotation is for making a call to target service via Zuul API Gateway
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeZuulProxy {
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, 
			@PathVariable("to") String to);

}
