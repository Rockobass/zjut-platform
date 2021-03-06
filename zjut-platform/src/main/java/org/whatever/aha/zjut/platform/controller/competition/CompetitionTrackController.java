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
import org.whatever.aha.zjut.platform.dto.competition.CompetitionTrackDto;
import org.whatever.aha.zjut.platform.service.competition.CompetitionTrackService;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/24 1:30
 */

@Api(tags = "赛事通道表  -   普通评审推荐    校团委推荐")
@RequestMapping("/v1/competitionTrack")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckRole(value = {AuthConst.R_supper, AuthConst.COMP_TAGS_LIST, AuthConst.R_school}, mode = SaMode.OR)
public class CompetitionTrackController {
    final CompetitionTrackService competitionTrackService;

    /**
     * 创建新的赛事通道信息
     * @param compId 竞赛id
     * @param competitionTrackDto 竞赛通道信息
     * @return
     */
    @ApiOperation("创建新的静态比赛映射信息")
    @PostMapping("/addCompInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "stageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "trackName", value = "竞赛通道名", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "trackDesc", value = "竞赛通道描述", dataTypeClass = String.class)
            @ApiImplicitParam(name = "competitionTrackDto", value = "竞赛通道信息", dataTypeClass = CompetitionTrackDto.class)
    })
    public Object addCompInfo(@RequestParam(value = "compId")int compId,
                              @RequestParam(value = "stageId")int stageId,
                              @RequestBody @Validated CompetitionTrackDto competitionTrackDto){
        return AjaxResult.SUCCESS(competitionTrackService.addTrack(compId, stageId, competitionTrackDto));
    }


    /**
     * 删除一条通道信息
     * @param compId 竞赛id
     * @param trackName 通道名
     * @return
     */
    @ApiOperation("删除一条tag信息")
    @DeleteMapping("/delTag/{compId}/{stageId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "stageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "trackName", value = "通道名", dataTypeClass = String.class)
    })
    public AjaxResult<Integer> delTag(@PathVariable("compId")int compId, @PathVariable("stageId")int stageId, @RequestParam("trackName")String trackName){
        return AjaxResult.SUCCESS(competitionTrackService.delTrack(compId, stageId, trackName));
    }
}
