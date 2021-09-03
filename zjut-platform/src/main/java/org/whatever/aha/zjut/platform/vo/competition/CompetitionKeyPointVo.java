package org.whatever.aha.zjut.platform.vo.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.lang.Nullable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/28 10:03
 */
@ApiModel("竞赛关键时间点信息Vo")
@NoArgsConstructor
@Data
public class CompetitionKeyPointVo {
    /**
     * 竞赛 id
     */
    @ApiModelProperty("竞赛 id")
    int compId;
    /**
     * 竞赛关键节点名
     */
    @ApiModelProperty("关键时间节点名")
    String  compKeyPointName;
    /**
     * 关键节点时间
     */
    @ApiModelProperty("关键时间节点")
    @NotNull(message = "时间节点不能为空！")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date compKeyTime;
    /**
     * 提醒的用户类型
     */
    @ApiModelProperty("关联用户类型")
    @Nullable
    Integer compUserType;
    /**
     * 时间点顺序
     */
    @ApiModelProperty("时间点顺序")
    @NotNull(message = "关键时间节点顺序不能为空")
    int compKeyPointOrder;
}
