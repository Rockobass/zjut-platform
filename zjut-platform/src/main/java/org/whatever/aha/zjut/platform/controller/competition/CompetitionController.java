package org.whatever.aha.zjut.platform.controller.competition;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.OperationType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.annotation.OperationLog;
import org.whatever.aha.zjut.platform.dto.competition.*;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageAward;
import org.whatever.aha.zjut.platform.service.competition.CompetitionService;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionDetailVo;
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
public class CompetitionController {
    final CompetitionService competitionService;

    /**
     * 分页获取竞赛粗略信息,默认时间排序
     *
     * @param compType  竞赛类型|1
     * @param compStatus  竞赛状态|1
     * @param compYear  竞赛年份|2021
     * @param pageNum   页数|1
     * @param pageSize  页大小|10
     * @return
     */
    @ApiOperation("分页获取所有竞赛粗略信息")
    @GetMapping("/getRoughInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛类型", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStatus", value = "竞赛状态", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compYear", value = "竞赛年份", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageNum", value = "页数", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    public List<CompetitionRoughVo> getCompetitionRoughPageable(@RequestParam(value = "compType", required = false) Integer compType,
                                                                @RequestParam(value = "compStatus", required = false) Integer compStatus,
                                                                @RequestParam(value = "compYear", required = false) Integer compYear,
                                                                @RequestParam(value = "pageNum", required = true, defaultValue = "0") int pageNum,
                                                                @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
        return competitionService.getCompetitionRoughPageable(compType, compStatus, compYear, (pageNum - 1) * pageSize, pageSize);
    }

    /**
     * 创建新的比赛全量信息
     *
     * @param competitionDetailDto  竞赛详细信息
     * @return
     */
    @SaCheckRole(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
    @ApiOperation("创建新的比赛详细信息")
    @PostMapping("/createCompetition")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "competitionDetailDto", value = "竞赛详细信息", dataTypeClass = CompetitionDetailDto.class)
    })
    public AjaxResult<String> createCompetition(@ApiParam(value = "竞赛详细信息",required = true) @RequestBody @Validated CompetitionDetailDto competitionDetailDto){
        return AjaxResult.SUCCESS(competitionService.addCompDetail(competitionDetailDto));
    }

    /**
     * 当前比赛进入下一阶段
     *
     * @param compId    竞赛id
     * @return
     */
    @SaCheckRole(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
    @ApiOperation("当前比赛进入下一个阶段")
    @PutMapping("/{compId}")
    public int enterNextStatus(@PathVariable("compId") int compId){
        return competitionService.enterNextStatus(compId);
    }

    /**
     * 获取当前赛事最新届数
     *
     * @param compId    竞赛id
     * @return
     */
    @ApiOperation("获取当前赛事最新届数")
    @GetMapping("/{compId}")
    public int getNewTh(@PathVariable("compId") int compId){
        return competitionService.getNewTh(compId);
    }
}
