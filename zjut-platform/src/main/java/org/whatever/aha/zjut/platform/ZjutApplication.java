package org.whatever.aha.zjut.platform;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Baby_mo
 */
@Slf4j
@EnableCaching
@SpringBootApplication(scanBasePackages = "org.whatever.aha.zjut.platform")
@ComponentScan(value = {"org.whatever.aha.zjut.platform", "org.whatever.aha.zjut.base"})
@EnableAsync
@EnableOpenApi
@MapperScan("org.whatever.aha.zjut.platform.mapper")
public class ZjutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjutApplication.class, args);
        log.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig().toString());
    }



    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
