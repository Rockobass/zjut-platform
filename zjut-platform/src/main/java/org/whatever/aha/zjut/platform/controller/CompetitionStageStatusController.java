package org.whatever.aha.zjut.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.service.CompetitionStageService;
import org.whatever.aha.zjut.platform.service.CompetitionStageStatusService;

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
    public int addCompStageStatus(int compStageId, String compStageStatusName, int compStageStatusOrder, Timestamp compStageStausEndTime, String compStageStausDesc){
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
    public int addCompStageStatusInBatch(List<Integer> compStageId, List<String> compStageStatusName, List<Integer> compStageStatusOrder, List<Timestamp> compStageStausEndTime, List<String> compStageStausDesc){
        return competitionStageStatusService.addCompStageStatusInBatch(compStageId,compStageStatusName,compStageStatusOrder,compStageStausEndTime,compStageStausDesc);
    }

    /**
     * 删除一条stage-status
     * @param compStageId    竞赛阶段Id竞赛Id
     * @param compStageStatusId    竞赛状态Id
     * @return
     */
    @ApiOperation("删除一条stage信息")
    @PostMapping("/delCompStageStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageStatusId", value = "竞赛阶段状态的名字Id", dataTypeClass = Integer.class)
    })
    public Object delCompStage(int compStageId, int compStageStatusId){
        return AjaxResult.SUCCESS(competitionStageStatusService.delCompStageStatus(compStageId, compStageStatusId));
    }

    /**
     * 获取当前阶段下一个阶段
     */
    @ApiOperation("获取当前阶段下一个阶段")
    @PostMapping("/getNextStageStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageStatusId", value = "竞赛阶段状态的Id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "curStageStatusOrder", value = "竞赛阶段状态顺序", dataTypeClass = Integer.class)
    })
    public Object getNextStage(int compStageId, int compStageStatusId, int curStageStatusOrder){
        return AjaxResult.SUCCESS(competitionStageStatusService.getNextStage(compStageId, compStageStatusId, curStageStatusOrder));
    }
}
