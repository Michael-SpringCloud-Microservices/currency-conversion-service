/**
 * 
 */
package com.in28minutes.microservices.currencyconversionservice.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 109726
 *
 */

@Controller
@RequestMapping
public class SwaggerUIController {
	
	public static final String SWAGGER_URL = "/swagger";
	
    public static final String CONTEXT_ROOT = "/";
    
   @RequestMapping(value={SWAGGER_URL,CONTEXT_ROOT} ,method = RequestMethod.GET)
   public String index() {
       return "redirect:/swagger-ui.html";
   }
}