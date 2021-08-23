package org.whatever.aha.zjut.base.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.whatever.aha.zjut.base.injector.EasySqlInjector;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:04
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    //需要注入的Bean
    @Bean
    public EasySqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }

    @Bean
    public GlobalConfig globalConfiguration() {
        GlobalConfig conf = new GlobalConfig();
        // 自定义的注入需要在这里进行配置
        conf.setSqlInjector(easySqlInjector());
        return conf;
    }
}
