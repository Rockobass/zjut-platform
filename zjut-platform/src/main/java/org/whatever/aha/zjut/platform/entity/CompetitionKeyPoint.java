package org.whatever.aha.zjut.platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:08
 */
@Data
@AllArgsConstructor
public class CompetitionKeyPoint {
    /**
     * 外键 竞赛id
     */
    @NotNull(message = "外键 竞赛id不能为空")
    int compId;
    /**
     * 关键时间节点名
     */
    @NotNull(message = "关键时间节点名不能为空")
    String compKeyPointName;
    /**
     * 关键时间节点
     */
    @NotNull(message = "关键时间节点不能为空")
    Timestamp compKeyTime;
    /**
     * 时间点是否需要提醒（0否 1是）
     */
    @NotNull(message = "时间点是否需要提醒不能为空")
    boolean compNeedAlert;
    /**
     * 关联用户类型
     */
    int compUserType;
}
