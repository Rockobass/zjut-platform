package org.whatever.aha.zjut.platform.entity.competition;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionStageStatus {
    /**
     * 竞赛阶段id
     */
    @Min(1)
    int compStageId;
    /**
     * 竞赛阶段状态id
     */
    @TableId(type = IdType.AUTO)
    @Min (1)
    Integer compStageStatusId;
    /**
     * 竞赛阶段状态的名字
     */
    String compStageStatusName;
    /**
     * 竞赛阶段状态顺序
     */
    @Min (1)
    int compStageStatusOrder;
    /**
     * 竞赛阶段状态结束时间
     */
    Date compStageStausEndTime;
    /**
     * 竞赛阶段状态描述
     */
    String compStageStausDesc;
}
