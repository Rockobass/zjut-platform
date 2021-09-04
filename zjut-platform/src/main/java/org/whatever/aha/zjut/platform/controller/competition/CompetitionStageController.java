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
import org.whatever.aha.zjut.platform.dto.competition.CompetitionStageDto;
import org.whatever.aha.zjut.platform.service.competition.CompetitionStageService;

import javax.validation.constraints.Min;
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
@SaCheckRole(value = {AuthConst.R_supper, AuthConst.R_school}, mode = SaMode.OR)
public class CompetitionStageController {
    final CompetitionStageService competitionStageService;

    @ApiOperation("创建新的赛事阶段信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "compStageName", value = "竞赛阶段名", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "nextStageNum", value = "可进入下一阶段数目", dataTypeClass = Integer.class),
//            @ApiImplicitParam(name = "compStageDesc", value = "阶段描述", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "stageOrder", value = "赛事阶段顺序", dataTypeClass = Integer.class)
            @ApiImplicitParam(name = "competitionStageDto", value = "赛事阶段信息", dataTypeClass = CompetitionStageDto.class)
    })
    @PostMapping("/addCompStage")
    public int addCompStage(@RequestParam(value = "compId") int compId,
                            @RequestBody CompetitionStageDto competitionStageDto){
        System.out.println("compId");
        System.out.println(competitionStageDto);
        return competitionStageService.addCompStage(compId,competitionStageDto);
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
    public int addCompStageInBatch(@RequestParam(value = "compId")List<Integer> compId,
                                   @RequestParam(value = "compStageName")List<String> compStageName,
                                   @RequestParam(value = "nextStageNum")List<Integer> nextStageNum,
                                   @RequestParam(value = "compStageDesc")List<String> compStageDesc,
            @RequestParam(value = "stageOrder")List<Integer> stageOrder){
              return competitionStageService.addCompStageInBatch(compId,compStageName,nextStageNum,compStageDesc,stageOrder);
    }

     /**
     *      * 删除一条stage
     * @param compStageId   竞赛阶段Id
     * @return
     */
    @ApiOperation("删除一条stage信息")
    @DeleteMapping("/delCompStage/{compId}/{compStageId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段Id", dataTypeClass = Integer.class)
    })
    public Object delCompStage(@PathVariable("compId")int compId,@PathVariable("compStageId") int compStageId){
        return AjaxResult.SUCCESS(competitionStageService.delCompStage(compId, compStageId));
    }

    /**
     * 获取当前阶段下一个阶段
     */
    @ApiOperation("获取当前阶段下一个阶段")
    @GetMapping("/getNextStage/{compId}/{compStageId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "compId", value = "竞赛id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "compStageId", value = "竞赛阶段Id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "curStageOrder", value = "竞赛顺序", dataTypeClass = Integer.class)
    })
    public Object getNextStage(@PathVariable("compId")int compId,@PathVariable("compStageId") int compStageId, @RequestParam(value = "curStageOrder") int curStageOrder){
        return AjaxResult.SUCCESS(competitionStageService.getNextStage(compId, compStageId, curStageOrder));
    }
}
