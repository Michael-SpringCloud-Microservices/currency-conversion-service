/**
 * 
 */
package com.in28minutes.microservices.currencyconversionservice.swagger.configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;

import java.util.Collections;
import java.util.List;

/**
 * @author 109726
 *
 */
public class DefaultSwaggerAppParameters implements SwaggerAppParameters{
    /**
     * Create global parameter that adds Authorization swagger token to each endpoint
     * http://springfox.github.io/springfox/docs/current
     */
    @Override
    public List<Parameter> globalOperationParameters() {
        return Collections.singletonList(new ParameterBuilder()
                .name("Authorization")
                .description("Authorization token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("Bearer X")
                .required(true)
                .build());
    }
}
