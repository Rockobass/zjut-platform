package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.RegexPattern;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.entity.StudentInfo;
import org.whatever.aha.zjut.platform.service.StudentInfoService;

import javax.validation.constraints.Pattern;
import java.text.ParseException;

@Api(tags = "学生相关接口")
@Validated
@RestController
@RequestMapping("/v1/stu")
@RequiredArgsConstructor
public class StudentController {
    final StudentInfoService studentInfoService;

    @ApiOperation("获取学生个人信息")
    @SaCheckRole("student")
    @GetMapping("/info")
    public Object getStudentInfo(){
        int userId = StpUtil.getLoginIdAsInt();
        StudentInfo dto = studentInfoService.getStudentInfo(userId);
        return AjaxResult.SUCCESS(dto);
    }

    @ApiOperation("修改学生个人信息")
    @SaCheckRole("student")
    @PostMapping("/info")
    public Object modifyStudentInfo(
            @RequestParam(required = false)  String studentNumber,
            @RequestParam(required = false) String realName, @RequestParam(required = false) @Range(min = 0, max = 1) Integer sex,
            @RequestParam(required = false) @Range(min = 0, max = 1) Integer degree,
            @RequestParam(required = false) @Pattern(regexp = RegexPattern.GRADE) String grade,
            @RequestParam(required = false) Integer academyId, @RequestParam(required = false) Integer majorId,
            @RequestParam(required = false) @Pattern(regexp = RegexPattern.DATE) String birthday,
            @RequestParam(required = false) @Pattern(regexp = RegexPattern.DATE) String admissionTime,
            @RequestParam(required = false) String schoolName,
            @RequestParam(required = false) String className
    ) throws ParseException {
        int userId = StpUtil.getLoginIdAsInt();
        studentInfoService.updateInfo(userId, studentNumber, realName, sex, degree, grade, academyId, majorId, birthday, admissionTime, schoolName, className);
        return AjaxResult.SUCCESS();
    }

    @ApiOperation(value = "检测学号是否存在", notes = "修改学号时调用")
    @PostMapping("/existStuId")
    public Object existStuId(@RequestParam String studentNumber) {
        int userId = StpUtil.getLoginIdAsInt();
        return AjaxResult.SUCCESS(studentInfoService.existStuNumber(userId, studentNumber));
    }
}
