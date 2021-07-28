package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.config.ProfileConfig;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.mapper.UserMapper;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Autowired
    ProfileConfig profileConfig;
    @Resource
    UserMapper userMapper;

    @PostMapping("/sa")
    AjaxResult<Object> test(@RequestParam int a){
        StpUtil.logoutByLoginId(10001);
        StpUtil.disable(10001, 86400);
        return AjaxResult.OK("成功");
    }

    @Cacheable(value = "common")
    @PostMapping("/tt")
    public AjaxResult<Object> t(int a){
        return AjaxResult.OK(userMapper.selectList(null));
    }
}
