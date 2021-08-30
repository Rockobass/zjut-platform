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
 * @date 2021/08/23 3:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionTrack {
    /**
     * comp id
     */
    @Min(1)
    int compId;
    /**
     * 赛事通道id
     */
    @TableId(type = IdType.AUTO)
    Integer trackId;
    /**
     * 赛事通道的名字
     */
    String trackName;
    /**
     * 赛事通道描述
     */
    String trackDesc;
}
