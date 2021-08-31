package org.whatever.aha.zjut.platform.dto.competition;

import lombok.Data;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 21:59
 */
@Data
public class CompetitionStageDto {
    /**
     * 阶段名字
     */
    @NotNull(message = "阶段名不能为空！")
    String stageName;
    /**
     * 推荐下一阶段数量
     */
    Integer nextStageNum;
    /**
     * 阶段描述
     */
    String stageDes;
    /**
     * 阶段顺序
     */
    @Min(1)
    @NotNull(message = "阶段顺序不能为空！")
    int stageOrder;

    public static CompetitionStage build(CompetitionStageDto dto) {
        return CompetitionStage.builder()
                .stageName(dto.stageName)
                .nextStageNum(dto.nextStageNum)
                .stageDes(dto.stageDes)
                .stageOrder(dto.stageOrder)
                .build();
    }
}
