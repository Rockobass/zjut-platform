package org.whatever.aha.zjut.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.exception.app.InvalidCredentialException;

@RestController
public class TestController {

    @RequestMapping("/")
    AjaxResult<Object> test(@RequestParam int a){
        if (a > 1)
            throw new InvalidCredentialException();
        return AjaxResult.OK("成功");
    }
}
