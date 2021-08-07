package org.whatever.aha.zjut.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author Baby_mo
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
