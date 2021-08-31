package org.whatever.aha.zjut.platform.entity.competition;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionKeyPoint {
    /**
     * 外键 竞赛id
     */
    @Min(1)
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
    Date compKeyTime;
    /**
     * 关联用户类型
     */
    @Nullable
    Integer compUserType;
    /**
     * 时间点顺序
     */
    @NotNull(message = "关键时间节点顺序不能为空")
    int compKeyPointOrder;
}
