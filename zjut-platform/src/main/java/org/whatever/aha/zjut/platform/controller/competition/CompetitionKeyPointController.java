package org.whatever.aha.zjut.platform.controller.competition;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionKeyPointDto;
import org.whatever.aha.zjut.platform.service.competition.CompetitionKeyPointService;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionKeyPointVo;

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
@SaCheckRole(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
public class CompetitionKeyPointController {
    final CompetitionKeyPointService competitionKeyPointService;

    /**
     * 创建新的比赛关键时间节点信息
     * @param compId                赛事ID
     * @param stageId               赛事阶段ID
     * @param competitionKeyPointDto      赛事关键节点信息
     * @return
     */
    @ApiOperation("创建新的比赛关键时间节点信息")
    @PostMapping("/addCompKeyPoint")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "comId", value = "竞赛id", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "compKeyPointName", value = "竞赛关键点名称", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "compSchoolEndTime", value = "竞赛关键点时间", dataTypeClass = Timestamp.class),
//            @ApiImplicitParam(name = "compUserType", value = "提醒用户类型", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "compKeyPointOrder", value = "竞赛关键点顺序", dataTypeClass = Integer.class)
//    })
    public AjaxResult<Integer> addCompKeyPoint(@RequestParam(value = "compId") int compId,
                                               @RequestParam(value = "satgeId") int stageId,
                                               @RequestBody @Validated CompetitionKeyPointDto competitionKeyPointDto){
        return AjaxResult.SUCCESS(competitionKeyPointService.addCompKeyPoint(compId,stageId,competitionKeyPointDto));
    }


    /**
     * 删除比赛关键时间节点信息
     */
    @ApiOperation("删除比赛关键时间节点信息")
    @DeleteMapping("/delCompKeyPoint/{compId}/{stageId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "stageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compKeyPointName", value = "竞赛关键点名称", dataTypeClass = String.class)
    })
    public AjaxResult<Integer> delCompKeyPoint(@PathVariable("compId")int compId,@PathVariable("stageId") int stageId,@RequestParam("compKeyPointName") String compKeyPointName){
        return AjaxResult.SUCCESS(competitionKeyPointService.delCompKeyPoint(compId, stageId, compKeyPointName));
    }

    /**
     * 获取某赛事对应的所有时间点信息
     */
    @ApiOperation("获取某赛事阶段对应的所有时间点信息")
    @GetMapping("/getCompKeyPoint/{compId}/{stageId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "stageId", value = "竞赛阶段id", dataTypeClass = Integer.class)
    })
    public AjaxResult<List<CompetitionKeyPointVo>> getCompKeyPoint(@PathVariable("compId")int compId, @PathVariable("stageId") int stageId){
        return AjaxResult.SUCCESS(competitionKeyPointService.getCompKeyPointVo(compId, stageId));
    }
}
