package org.whatever.aha.zjut.platform.entity.competition;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/19 22:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionStage {
    /**
     * 竞赛id
     */
    @Min(1)
    int compId;
    /**
     * 阶段id
     */
    @TableId(type = IdType.AUTO)
    @Min (1)
    Integer stageId;
    /**
     * 阶段名字
     */
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
    @Min (1)
    int stageOrder;
}
