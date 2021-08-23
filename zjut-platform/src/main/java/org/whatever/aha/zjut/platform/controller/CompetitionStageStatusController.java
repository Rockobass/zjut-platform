package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.service.CompetitionStageService;
import org.whatever.aha.zjut.platform.service.CompetitionStageStatusService;

import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:59
 */
@Api(tags = "赛事小阶段数据接口")
@RequestMapping("/v1/competitionStageStatus")
@RequiredArgsConstructor
@RestController
@SaCheckPermission(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
public class CompetitionStageStatusController {
    final CompetitionStageStatusService competitionStageStatusService;

    @ApiOperation("创建新的赛事小阶段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageStatusName", value = "竞赛阶段状态的名字", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compStageStatusOrder", value = "竞赛阶段状态顺序", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageStatusOrder", value = "竞赛阶段状态结束时间", dataTypeClass = String.class),
            @ApiImplicitParam(name = "compStageStausDesc", value = "竞赛阶段状态描述", dataTypeClass = Integer.class)
    })
    @PostMapping("/addCompStageStatus")
    public int addCompStageStatus(@RequestParam(value = "compStageId")int compStageId,
                                  @RequestParam(value = "compStageStatusName")String compStageStatusName,
                                  @RequestParam(value = "compStageStatusOrder")int compStageStatusOrder,
                                  @RequestParam(value = "compStageStausEndTime")Timestamp compStageStausEndTime,
                                  @RequestParam(value = "compStageStausDesc", required = false)String compStageStausDesc){
        return competitionStageStatusService.addCompStageStatus(compStageId,compStageStatusName,compStageStatusOrder,compStageStausEndTime,compStageStausDesc);
    }

    @ApiOperation("批量创建新的赛事阶段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛阶段id", allowMultiple = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageName", value = "竞赛阶段名", allowMultiple = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nextStageNum", value = "可进入下一阶段数目", allowMultiple = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageDesc", value = "阶段描述", allowMultiple = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "stageOrder", value = "赛事阶段顺序", allowMultiple = true, dataTypeClass = Integer.class)
    })
    @PostMapping("/addCompStageStatusInBatch")
    public int addCompStageStatusInBatch(@RequestParam(value = "compStageId")List<Integer> compStageId,
                                         @RequestParam(value = "compStageStatusName")List<String> compStageStatusName,
                                         @RequestParam(value = "compStageStatusOrder")List<Integer> compStageStatusOrder,
                                         @RequestParam(value = "compStageStausEndTime")List<Timestamp> compStageStausEndTime,
                                         @RequestParam(value = "compStageStausDesc")List<String> compStageStausDesc){
        return competitionStageStatusService.addCompStageStatusInBatch(compStageId,compStageStatusName,compStageStatusOrder,compStageStausEndTime,compStageStausDesc);
    }

    /**
     * 删除一条stage-status
     * @param stageId    竞赛阶段Id
     * @param statusId    竞赛状态Id
     * @return
     */
    @ApiOperation("删除一条stage信息")
    @DeleteMapping("/delCompStageStatus/{stageId}/{statusId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "statusId", value = "竞赛阶段状态的Id", dataTypeClass = Integer.class)
    })
    public Object delCompStage(@PathVariable("stageId")int stageId,@PathVariable("statusId") int statusId){
        return AjaxResult.SUCCESS(competitionStageStatusService.delCompStageStatus(stageId, statusId));
    }

    /**
     * 获取当前阶段下一个阶段
     */
    @ApiOperation("获取当前阶段下一个阶段")
    @GetMapping("/getNextStageStatus/{compStageId}/{compStageStatusId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageStatusId", value = "竞赛阶段状态的Id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "curStageStatusOrder", value = "竞赛阶段状态顺序", dataTypeClass = Integer.class)
    })
    public Object getNextStage(@PathVariable("compStageId")int compStageId, @PathVariable("compStageStatusId") int compStageStatusId, @RequestParam(value = "curStageStatusOrder") int curStageStatusOrder){
        return AjaxResult.SUCCESS(competitionStageStatusService.getNextStage(compStageId, compStageStatusId, curStageStatusOrder));
    }
}
