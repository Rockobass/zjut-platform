package org.whatever.aha.zjut.platform.vo.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/28 10:03
 */
public class CompetitionKeyPointVo {
    /**
     * 竞赛 id
     */
    int compId;
    /**
     * 竞赛关键节点名
     */
    String  compKeyPointName;
    /**
     * 关键节点时间
     */
    @NotNull(message = "时间节点不能为空！")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date compKeyTime;
    /**
     * 提醒的用户类型
     */
    @Nullable
    Integer userType;
    /**
     * 时间点顺序
     */
    @NotNull(message = "关键时间节点顺序不能为空")
    int compKeyPointOrder;
}
