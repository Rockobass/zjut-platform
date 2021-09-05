package org.whatever.aha.zjut.platform.dto.competition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageAward;

import javax.validation.constraints.NotNull;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 22:15
 */
@Data
@ApiModel("竞赛阶段奖励信息Dto")
public class CompetitionStageAwardDto {
    /**
     * 阶段id
     */
    @ApiModelProperty("阶段id")
    @NotNull(message = "阶段Id不能为空")
    int stageId;
    /**
     * 奖项名称
     */
    @ApiModelProperty("奖项名称")
    @NotNull(message = "奖项名称不能为空")
    String awardName;
    /**
     * 奖项百分比
     */
    @ApiModelProperty("奖项百分比")
    int awardPercent;
    /**
     * 奖项数量
     */
    @ApiModelProperty("奖项数量")
    int awardAmt;

    public static CompetitionStageAward build(CompetitionStageAwardDto dto) {
        return CompetitionStageAward.builder()
                .awardName(dto.awardName)
                .awardAmt(dto.awardAmt)
                .awardPercent(dto.awardPercent)
                .build();
    }
}
