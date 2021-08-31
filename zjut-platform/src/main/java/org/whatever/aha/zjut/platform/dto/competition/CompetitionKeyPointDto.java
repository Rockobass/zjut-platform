package org.whatever.aha.zjut.platform.dto.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionKeyPoint;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 21:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionKeyPointDto {
    /**
     * 关键时间节点名
     */
    @NotNull(message = "关键时间节点名不能为空")
    String compKeyPointName;
    /**
     * 关键时间节点
     */
    @NotNull(message = "关键时间节点不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
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

    public static CompetitionKeyPoint build(CompetitionKeyPointDto dto) {
        return CompetitionKeyPoint.builder()
                .compKeyPointName(dto.compKeyPointName)
                .compKeyPointOrder(dto.compKeyPointOrder)
                .compKeyTime(dto.compKeyTime)
                .compUserType(dto.compUserType)
                .build();
    }
}
