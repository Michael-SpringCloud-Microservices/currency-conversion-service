/**
 * 
 */
package com.in28minutes.microservices.currencyconversionservice.swagger.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 109726
 *
 */
@Configuration
public class SwaggerUIConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Michael Philomin Raj", 
			"http://www.in28minutes.com", "michaelraj.p@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Currency Conversion Swagger UI", "Currency Conversion API Documentation",
			"1.0", "urn:tos",DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json","application/xml"));

	@Bean
	@ConditionalOnMissingBean(SwaggerAppParameters.class)
	public SwaggerAppParameters swaggerAppParameters() {
		return new DefaultSwaggerAppParameters();
	}


	// Bean - Docket
	@Bean 
	public Docket api(SwaggerAppParameters swaggerAppParameters){
		return new Docket(DocumentationType.SWAGGER_2)
		.select().apis(RequestHandlerSelectors.basePackage("com.in28minutes.microservices.currencyconversionservice.controller"))
		.paths(PathSelectors.any()).build()
		.apiInfo(DEFAULT_API_INFO)
		.globalOperationParameters(swaggerAppParameters.globalOperationParameters())
		.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
		.produces(DEFAULT_PRODUCES_AND_CONSUMES);
	}
	// Version Swagger 2
	// All the paths
	// All the apis

}
