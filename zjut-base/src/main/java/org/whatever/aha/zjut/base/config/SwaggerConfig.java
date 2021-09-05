package org.whatever.aha.zjut.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;


/**
 * swagger访问地址为/doc.html
 * @author Baby_mo
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.whatever.aha.zjut.platform"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()// 支持的通讯协议集合
                .protocols(getProtocols("https", "http"));
    }

    private HashSet<String> getProtocols(String https, String http) {
        HashSet set = new HashSet<String>();
        set.add(https);
        set.add(http);
        return set;
    }
}
