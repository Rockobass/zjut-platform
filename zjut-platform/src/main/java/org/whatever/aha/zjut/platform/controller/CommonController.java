package org.whatever.aha.zjut.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionTypeInfo;
import org.whatever.aha.zjut.platform.service.CommonService;
import org.whatever.aha.zjut.platform.service.competition.CompetitionStaticTagsService;
import org.whatever.aha.zjut.platform.service.competition.CompetitionTypeInfoService;

import java.util.List;

/**
 * @author Baby_mo
 */
@Api(tags = "无需鉴权的一些数据接口")
@RequestMapping("/v1/common")
@RequiredArgsConstructor
@RestController
public class CommonController {
    final CommonService commonService;
    final CompetitionStaticTagsService competitionStaticTagsService;
    final CompetitionTypeInfoService competitionTypeInfoService;

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

    @ApiOperation("根据竞赛类型获取固定的竞赛分组信息")
    @ApiImplicitParam(name = "compType", value = "赛事类型", dataTypeClass = Integer.class)
    @GetMapping("/compGroups")
    public Object getCompGroupList(@RequestParam int compType){
        return AjaxResult.SUCCESS(competitionStaticTagsService.getGroupByType(compType));
    }

    @ApiOperation("根据竞赛类型和组名获取固定的竞赛标签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛类型", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组名", dataTypeClass = String.class)
    })
    @GetMapping("/compTags")
    public Object getCompTagList(@RequestParam int compType,@RequestParam String compGroup){
        return AjaxResult.SUCCESS(competitionStaticTagsService.getTags(compType, compGroup));
    }

    @ApiOperation("获得所有的竞赛类型ID和类型名称")
    @GetMapping("/getCompTypeInfo")
    public AjaxResult<List<CompetitionTypeInfo>> getCompTypeInfo(){
        return AjaxResult.SUCCESS(competitionTypeInfoService.getAll());
    }
}
