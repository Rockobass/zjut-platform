package org.whatever.aha.zjut.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.service.CommonService;

/**
 * @author Baby_mo
 */
@Api(tags = "无需鉴权的一些数据接口")
@RequestMapping("/v1/common")
@RequiredArgsConstructor
@RestController
public class CommonController {
    final CommonService commonService;

    @ApiOperation("获取学院列表")
    @GetMapping("/academyList")
    public Object getAcademyList() {
        return AjaxResult.SUCCESS(commonService.getAcademyList());
    }

    @ApiOperation("获取学院下的专业")
    @GetMapping("/majorList")
    public Object getMajorList(@RequestParam int academyId) {
        return AjaxResult.SUCCESS(commonService.getMajorList(academyId));
    }
}
