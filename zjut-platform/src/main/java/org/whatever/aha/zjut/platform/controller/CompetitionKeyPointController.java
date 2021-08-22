package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.entity.CompetitionKeyPoint;
import org.whatever.aha.zjut.platform.service.CompetitionKeyPointService;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc 赛事关键时间点操作类
 * @date 2021/08/16 1:44
 */
@Api(tags = "赛事关键时间点操作类")
@RequestMapping("/v1/competitionKeyPoint")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckPermission(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
public class CompetitionKeyPointController {
    final CompetitionKeyPointService competitionKeyPointService;

    /**
     * 创建新的比赛关键时间节点信息
     * @param compId                赛事ID
     * @param compKeyPointName      赛事关键时间点名称
     * @param compSchoolEndTime     关键点日期
     * @param compNeedAlert         是否需要提醒
     * @param compUserType          提醒用户类型
     * @return
     */
    @ApiOperation("创建新的比赛关键时间节点信息")
    @PostMapping("/addCompKeyPoint")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compKeyPointName", value = "竞赛关键点名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compSchoolEndTime", value = "竞赛关键点时间", dataTypeClass = Timestamp.class),
            @ApiImplicitParam(name = "compNeedAlert", value = "是否需要提醒", dataTypeClass = Boolean.class),
            @ApiImplicitParam(name = "compUserType", value = "提醒用户类型", dataTypeClass = Integer.class)
    })
    public Object addCompKeyPoint(int compId, String compKeyPointName, Timestamp compSchoolEndTime, boolean compNeedAlert, int compUserType){
        return AjaxResult.SUCCESS(competitionKeyPointService.addCompKeyPoint(compId, compKeyPointName, compSchoolEndTime, compNeedAlert, compUserType));
    }


    /**
     * 删除比赛关键时间节点信息
     */
    @ApiOperation("删除比赛关键时间节点信息")
    @DeleteMapping("/delCompKeyPoint")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compKeyPointName", value = "竞赛关键点名称", dataTypeClass = String.class)
    })
    public Object delCompKeyPoint(int compId, String compKeyPointName){
        return AjaxResult.SUCCESS(competitionKeyPointService.delCompKeyPoint(compId, compKeyPointName));
    }

    /**
     * 获取某赛事对应的所有时间点信息
     */
    @ApiOperation("获取某赛事对应的所有时间点信息")
    @DeleteMapping("/getCompKeyPoint")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class)
    })
    public Object getCompKeyPoint(int compId){
        return AjaxResult.SUCCESS(competitionKeyPointService.getCompKeyPoint(compId));
    }
}
