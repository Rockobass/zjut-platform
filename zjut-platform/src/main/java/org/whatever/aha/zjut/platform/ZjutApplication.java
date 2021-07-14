package org.whatever.aha.zjut.platform;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "org.whatever.aha.zjut.platform")
@ComponentScan(value = {"org.whatever.aha.zjut.platform", "org.whatever.aha.zjut.base"})
@Slf4j
public class ZjutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZjutApplication.class, args);
        log.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig().toString());
    }
}
