package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:33
 */
@Data
@AllArgsConstructor
public class CompetitionStageStatus {
    /**
     * 竞赛阶段id
     */
    int comp_stage_id;
    /**
     * 竞赛阶段状态id
     */
    @TableId(type = IdType.AUTO)
    Integer comp_stage_status_id;
    /**
     * 竞赛阶段状态的名字
     */
    String comp_stage_status_name;
    /**
     * 竞赛阶段状态顺序
     */
    int comp_stage_status_order;
    /**
     * 竞赛阶段状态结束时间
     */
    Timestamp comp_stage_staus_end_time;
    /**
     * 竞赛阶段状态描述
     */
    String comp_stage_staus_desc;
}
