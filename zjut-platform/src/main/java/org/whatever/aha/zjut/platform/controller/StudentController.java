package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.dto.StudentInfoDto;
import org.whatever.aha.zjut.platform.service.StudentInfoService;

@Api(tags = "学生相关接口")
@Validated
@RestController
@RequestMapping("/v1/stu")
@SaCheckLogin
@RequiredArgsConstructor
public class StudentController {
    final StudentInfoService studentInfoService;

    @ApiOperation("获取学生信息")
    @GetMapping("/info")
    public Object getStudentInfo(){
        int userId = StpUtil.getLoginIdAsInt();
        StudentInfoDto dto = studentInfoService.getStudentInfo(userId);
        return AjaxResult.SUCCESS(dto);
    }
}
