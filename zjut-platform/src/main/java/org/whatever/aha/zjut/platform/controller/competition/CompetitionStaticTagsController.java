package org.whatever.aha.zjut.platform.controller.competition;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.service.competition.CompetitionStaticTagsService;

/**
 * @author Vc
 * @version 1.0
 * @Desc   后台 - 静态赛事分组标签实体增删改
 * @date 2021/08/16 1:31
 */
@Api(tags = "后台 - 静态赛事分组标签实体增删改")
@RequestMapping("/v1/competitionStaticTags")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckRole(value = {AuthConst.R_supper, AuthConst.COMP_TAGS_LIST, AuthConst.R_school}, mode = SaMode.OR)
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
    public Object addCompInfo(@RequestParam(value = "compType")int compType,
                              @RequestParam(value = "compGroup")String compGroup,
                              @RequestParam(value = "compTag")String compTag){
        return AjaxResult.SUCCESS(competitionStaticTagsService.addCompInfo(compType, compGroup, compTag));
    }


    /**
     * 删除一条tag信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     * @param compTag 竞赛标签
     */
    @ApiOperation("删除一条tag信息")
    @DeleteMapping("/delTag/{compType}/{compGroup}/{compTag}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compTag", value = "竞赛标签", dataTypeClass = String.class)
    })
    public Object delTag(@PathVariable("compType")int compType,@PathVariable("compGroup") String compGroup,@PathVariable(value = "compTag") String compTag){
        return AjaxResult.SUCCESS(competitionStaticTagsService.delTag(compType, compGroup, compTag));
    }



    /**
     * 删除compGroup对应的所有Tag信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     */
    @ApiOperation("删除compGroup对应的所有Tag信息")
    @DeleteMapping("/delTagByGroup/{compType}/{compGroup}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class)
    })
    public Object delTagByGroup(@PathVariable("compType")int compType,@PathVariable("compGroup") String compGroup){
        return AjaxResult.SUCCESS(competitionStaticTagsService.delTagByGroup(compType, compGroup));
    }


    /**
     * 批量删除tag信息
     * @param compType 竞赛id
     * @param compGroup 竞赛组别
     * @param compTagList 竞赛标签列表
     */
    @ApiOperation("批量删除tag信息")
    @DeleteMapping("/delTagInBatch/{compType}/{compGroup}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compGroup", value = "竞赛组别", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compTag", value = "竞赛标签", allowMultiple = true, dataTypeClass = String.class)
    })
    public Object delTagInBatch(@PathVariable("compType")int compType,@PathVariable("compGroup") String compGroup,@RequestParam(value = "compTagList") String[] compTagList){
        return AjaxResult.SUCCESS(competitionStaticTagsService.delTagInBatch(compType, compGroup, compTagList));
    }
}
