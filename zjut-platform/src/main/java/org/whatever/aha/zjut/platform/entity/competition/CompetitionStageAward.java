package org.whatever.aha.zjut.platform.entity.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Vc
 * @version 1.0
 * @Desc  每个大阶段评出一定数量的奖项，譬如初赛评出部分三等奖，复赛评出部分三等奖二等奖，决赛评一等特等
 * @date 2021/08/25 0:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionStageAward {
    /**
     * 赛事 id
     */
    @NotNull(message = "赛事Id不能为空")
    int compId;
    /**
     * 阶段id
     */
    @NotNull(message = "赛事阶段不能为空")
    int stageId;
    /**
     * 奖项名称
     */
    @NotNull(message = "奖项名称不能为空")
    String awardName;
    /**
     * 奖项百分比
     */
    int awardPercent;
    /**
     * 奖项数量
     */
    int awardAmt;
}
