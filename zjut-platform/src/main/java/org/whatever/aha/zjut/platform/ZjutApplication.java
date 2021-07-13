package org.whatever.aha.zjut.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "org.whatever.aha.zjut.platform")
@ComponentScan(value = {"org.whatever.aha.zjut.platform", "org.whatever.aha.zjut.base" })
public class ZjutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjutApplication.class, args);
    }
}
