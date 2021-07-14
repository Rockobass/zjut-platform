package org.whatever.aha.zjut.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.exception.app.AccountBlockedException;

@RestController
public class TestController {

    @RequestMapping("/sa")
    AjaxResult<Object> test(@RequestParam int a){
        if (a > 1)
            throw new AccountBlockedException("231");
        return AjaxResult.OK("成功");
    }
}
