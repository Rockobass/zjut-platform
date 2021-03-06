package org.whatever.aha.zjut.platform.dto.competition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.whatever.aha.zjut.platform.entity.competition.AcademyCompetition;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 22:22
 */
@Data
@ApiModel("学院-竞赛信息Dto")
public class AcademyCompetitionDto {
    /**
     * 学院id
     */
    @ApiModelProperty("学院id")
    int academyId;
    /**
     * 学院推荐进入下一阶段剩余数量
     */
    @ApiModelProperty("学院推荐进入下一阶段剩余数量")
    int academyRecAmt;
    /**
     * 院级自评三等奖数量
     */
    @ApiModelProperty("院级自评三等奖数量")
    int thirdPrizeAmth;

    public static AcademyCompetition build(AcademyCompetitionDto dto) {
        return AcademyCompetition.builder()
                .academyRecAmt(dto.academyRecAmt)
                .thirdPrizeAmth(dto.thirdPrizeAmth)
                .build();
    }
}
