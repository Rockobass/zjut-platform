package org.whatever.aha.zjut.platform.dto.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 22:17
 */
@Data
@ApiModel(description = "竞赛阶段状态信息Dto")
public class CompetitionStageStatusDto {
    /**
     * 竞赛阶段状态id
     */
    @ApiModelProperty(value = "竞赛阶段状态id", name = "compStageId", required = true )
    @NotNull(message = "关键时间节点不能为空")
    int compStageId;
    /**
     * 竞赛阶段状态的名字
     */
    @ApiModelProperty(value = "竞赛阶段状态的名字", name = "compStageStatusName", required = true )
    @NotNull(message = "竞赛阶段状态的名字不能为空")
    String compStageStatusName;
    /**
     * 竞赛阶段状态顺序
     */
    @ApiModelProperty(value = "竞赛阶段状态顺序", name = "compStageStatusOrder", required = true )
    @Min(1)
    @NotNull(message = "竞赛阶段状态顺序不能为空")
    int compStageStatusOrder;
    /**
     * 竞赛阶段状态结束时间
     */
    @ApiModelProperty(value = "竞赛阶段状态结束时间", name = "compStageStausEndTime", required = true )
    @NotNull(message = "竞赛阶段状态结束时间不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    Date compStageStausEndTime;
    /**
     * 竞赛阶段状态描述
     */
    @ApiModelProperty(value = "竞赛阶段状态描述", name = "compStageStausDesc")
    String compStageStausDesc;

    public static CompetitionStageStatus build(CompetitionStageStatusDto dto) {
        return CompetitionStageStatus.builder()
                .compStageStatusName(dto.compStageStatusName)
                .compStageStatusOrder(dto.compStageStatusOrder)
                .compStageStausEndTime(dto.compStageStausEndTime)
                .compStageStausDesc(dto.compStageStausDesc)
                .build();
    }
}

