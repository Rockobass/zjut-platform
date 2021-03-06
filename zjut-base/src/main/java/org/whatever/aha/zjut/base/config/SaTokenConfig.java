package org.whatever.aha.zjut.base.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Baby_mo
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
            // 根据路由划分模块，不同模块不同鉴权
            SaRouter.match("/v1/**", StpUtil::checkLogin);

        })).addPathPatterns("/v1/**").excludePathPatterns("/v1/sa/**", "/v1/test/**", "/v1/common/**");

        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
    }
}
