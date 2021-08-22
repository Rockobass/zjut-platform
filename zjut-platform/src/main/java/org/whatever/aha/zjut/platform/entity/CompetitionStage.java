package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/19 22:06
 */
@Data
@AllArgsConstructor
public class CompetitionStage {
    /**
     * 竞赛id
     */
    int comp_id;
    /**
     * 阶段id
     */
    @TableId(type = IdType.AUTO)
    Integer stage_id;
    /**
     * 阶段名字
     */
    String stage_name;
    /**
     * 推荐下一阶段数量
     */
    Integer next_stage_num;
    /**
     * 阶段描述
     */
    String stage_des;
    /**
     * 阶段顺序
     */
    int stage_order;
}
