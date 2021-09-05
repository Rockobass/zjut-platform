package org.whatever.aha.zjut.platform.controller.competition;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionDetailDto;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionStageStatusDto;
import org.whatever.aha.zjut.platform.service.competition.CompetitionService;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionKeyPointVo;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionRoughVo;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/25 0:58
 */
@Api(tags = "赛事相关操作类")
@RequestMapping("/v1/competition")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckPermission(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
public class CompetitionController {
    final CompetitionService competitionService;

    /**
     * 分页获取竞赛粗略信息
     */
    @ApiOperation("分页获取竞赛粗略信息")
    @GetMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛类型", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageNum", value = "页数", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    public List<CompetitionRoughVo> getCompetitionRoughPageable(@RequestParam(value = "compType", required = true) int compType,
                                                                @RequestParam(value = "pageNum", required = true, defaultValue = "0") int pageNum,
                                                                @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
        return competitionService.getCompetitionRoughPageable(compType, (pageNum - 1) * pageSize, pageSize);
    }

    /**
     * 创建新的比赛全量信息
     */
    @ApiOperation("创建新的比赛详细信息")
    @PostMapping("/createCompetition")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "comId", value = "竞赛id", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "competitionDetailDto", value = "竞赛详细信息", dataTypeClass = CompetitionDetailDto.class)
//    })
    public int createCompetition(@ApiParam(value = "竞赛id",required = true) @RequestParam(value = "compId")int compId,
                                 @ApiParam(value = "竞赛详细信息",required = true) @RequestBody @Validated CompetitionDetailDto competitionDetailDto){
        return competitionService.addCompDetail(compId, competitionDetailDto);
    }

    /**
     * 创建新的比赛全量信息
     */
    @ApiOperation("创建新的比赛详细信息")
    @PostMapping("/createCompetition1")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "comId", value = "竞赛id", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "competitionDetailDto", value = "竞赛详细信息", dataTypeClass = CompetitionDetailDto.class)
//    })
    public CompetitionKeyPointVo createCompetition(@RequestParam(value = "compId")int compId,
                                  @RequestBody CompetitionStageStatusDto competitionStageStatusDtop){
        return new CompetitionKeyPointVo();
    }

}
