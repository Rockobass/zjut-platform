package org.whatever.aha.zjut.platform.vo.competition;

import lombok.Data;
import org.junit.experimental.theories.DataPoints;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/28 9:28
 */
@Data
public class CompetitionStageVo {
    /**
     * 阶段id
     */
   int stageId;
    /**
     * 阶段名
     */
    String stageName;
    /**
     * 下一阶段num
     */
    int nextStageNum;
    /**
     * 阶段描述
     */
    String stageDes;
    /**
     * 阶段顺序
     */
    int stageOrder;
}
