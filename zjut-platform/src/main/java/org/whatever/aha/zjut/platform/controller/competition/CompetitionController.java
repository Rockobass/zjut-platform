package org.whatever.aha.zjut.platform.controller.competition;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.platform.dto.competition.*;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageAward;
import org.whatever.aha.zjut.platform.service.competition.CompetitionService;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionDetailVo;
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
    @PostMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compType", value = "竞赛类型", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageNum", value = "页数", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    public List<CompetitionRoughVo> getCompetitionRoughPageable(@RequestParam(value = "compType", required = true) int compType,
                                                                @RequestParam(value = "pageNum", required = true, defaultValue = "0") Integer pageNum,
                                                                @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize) {
        Page<CompetitionRoughVo> page = new Page<CompetitionRoughVo>(pageNum, pageSize);
        return competitionService.getCompetitionRoughPageable(compType, page);
    }

    /**
     * 创建新的比赛全量信息
     */
    @ApiOperation("创建新的比赛详细信息")
    @PostMapping("/createCompetition")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "competitionDetailDto", value = "竞赛详细信息", dataTypeClass = CompetitionDetailVo.class)
    })
    public int createCompetition(@RequestParam(value = "compId")int compId,
                                 @RequestBody @Validated CompetitionDetailDto competitionDetailDto){
        return competitionService.addCompDetail(compId, competitionDetailDto);
    }

}
