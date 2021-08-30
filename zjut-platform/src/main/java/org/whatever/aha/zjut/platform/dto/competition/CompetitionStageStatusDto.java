package org.whatever.aha.zjut.platform.dto.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CompetitionStageStatusDto {
    /**
     * 竞赛阶段状态id
     */
    @NotNull(message = "关键时间节点不能为空")
    int compStageId;
    /**
     * 竞赛阶段状态的名字
     */
    @NotNull(message = "竞赛阶段状态的名字不能为空")
    String compStageStatusName;
    /**
     * 竞赛阶段状态顺序
     */
    @Min(1)
    @NotNull(message = "竞赛阶段状态顺序不能为空")
    int compStageStatusOrder;
    /**
     * 竞赛阶段状态结束时间
     */
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

