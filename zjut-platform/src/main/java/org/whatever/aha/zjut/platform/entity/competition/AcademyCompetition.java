package org.whatever.aha.zjut.platform.entity.competition;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 20:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademyCompetition {
    /**
     * 赛事id
     */
    int compId;
    /**
     * 学院id
     */
    int academyId;
    /**
     * 学院推荐进入下一阶段剩余数量
     */
    int academyRecAmt;
    /**
     * 院级自评三等奖数量
     */
    int thirdPrizeAmth;
}
