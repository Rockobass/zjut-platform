package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.entity.CompetitionStaticTags;
import org.whatever.aha.zjut.platform.service.CompetitionKeyPointService;
import org.whatever.aha.zjut.platform.service.CompetitionStaticTagsService;

/**
 * @author Vc
 * @version 1.0
 * @Desc   后台 - 静态赛事分组标签实体增删改
 * @date 2021/08/16 1:31
 */
@Api(tags = "后台 - 静态赛事分组标签实体增删改")
@RequestMapping("/CompetitionStaticTags")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckPermission(value = {AuthConst.R_supper, AuthConst.COMP_TAGS_LIST}, mode = SaMode.OR)
public class CompetitionStaticTagsController {
    final CompetitionStaticTagsService competitionStaticTagsService;


    /**
     * 创建新的静态比赛映射信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     * @param compTag 竞赛标签
     */
    @ApiOperation("创建新的静态比赛映射信息")
    @PostMapping("/addCompInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compTag", value = "竞赛标签", dataTypeClass = String.class)
    })
    public Object addCompInfo(int compType, String compGroup, String compTag){
        return AjaxResult.SUCCESS(competitionStaticTagsService.addCompInfo(compType, compGroup, compTag));
    }


    /**
     * 删除一条tag信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     * @param compTag 竞赛标签
     */
    @ApiOperation("删除一条tag信息")
    @PostMapping("/delTag")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compTag", value = "竞赛标签", dataTypeClass = String.class)
    })
    public Object delTag(int compType, String compGroup, String compTag){
        return AjaxResult.SUCCESS(competitionStaticTagsService.delTag(compType, compGroup, compTag));
    }



    /**
     * 删除compGroup对应的所有Tag信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     */
    @ApiOperation("删除compGroup对应的所有Tag信息")
    @PostMapping("/delTags")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class)
    })
    public Object delTag(int compType, String compGroup){
        return AjaxResult.SUCCESS(competitionStaticTagsService.delTagByGroup(compType, compGroup));
    }


    /**
     * 批量删除tag信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     * @param compTagList 竞赛标签列表
     */
    @ApiOperation("批量删除tag信息")
    @PostMapping("/delTagInBatch")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compTag", value = "竞赛标签", allowMultiple = true, dataTypeClass = String.class)
    })
    public Object delTagInBatch(int compType, String compGroup, String[] compTagList){
        return AjaxResult.SUCCESS(competitionStaticTagsService.delTagInBatch(compType, compGroup, compTagList));
    }
}
