package org.whatever.aha.zjut.platform.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.entity.CompetitionStage;
import org.whatever.aha.zjut.platform.service.CompetitionStageService;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 2:42
 */
@Api(tags = "赛事阶段数据接口")
@RequestMapping("/v1/competitionStage")
@RequiredArgsConstructor
@RestController
public class CompetitionStageController {
    final CompetitionStageService competitionStageService;

    @ApiOperation("创建新的赛事阶段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageName", value = "竞赛阶段名", dataTypeClass = String.class),
            @ApiImplicitParam(name = "nextStageNum", value = "可进入下一阶段数目", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageDesc", value = "阶段描述", dataTypeClass = String.class),
            @ApiImplicitParam(name = "stageOrder", value = "赛事阶段顺序", dataTypeClass = Integer.class)
    })
    @PostMapping("/addCompStage")
    public int addCompStage(int compId, String compStageName, int nextStageNum, String compStageDesc, int stageOrder){
        return competitionStageService.addCompStage(compId,compStageName,nextStageNum,compStageDesc,stageOrder);
    }

    @ApiOperation("批量创建新的赛事阶段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", allowMultiple = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageName", value = "竞赛阶段名", allowMultiple = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nextStageNum", value = "可进入下一阶段数目", allowMultiple = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageDesc", value = "阶段描述", allowMultiple = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "stageOrder", value = "赛事阶段顺序", allowMultiple = true, dataTypeClass = Integer.class)
    })
    @PostMapping("/addCompStageInBatch")
    public int addCompStageInBatch(List<Integer> compId, List<String> compStageName, List<Integer> nextStageNum, List<String> compStageDesc, List<Integer> stageOrder){
        return competitionStageService.addCompStageInBatch(compId,compStageName,nextStageNum,compStageDesc,stageOrder);
    }

    /**
     * 删除一条stage
     * @param compId    竞赛Id
     * @param compStageId   竞赛阶段Id
     * @return
     */
    @ApiOperation("删除一条stage信息")
    @PostMapping("/delCompStage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段Id", dataTypeClass = Integer.class)
    })
    public Object delCompStage(int compId, int compStageId){
        return AjaxResult.SUCCESS(competitionStageService.delCompStage(compId, compStageId));
    }

    /**
     * 获取当前阶段下一个阶段
     */
    @ApiOperation("获取当前阶段下一个阶段")
    @PostMapping("/getNextStage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段Id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "curStageOrder", value = "竞赛顺序", dataTypeClass = Integer.class)
    })
    public Object getNextStage(int compId, int compStageId, int curStageOrder){
        return AjaxResult.SUCCESS(competitionStageService.getNextStage(compId, compStageId, curStageOrder));
    }
}
