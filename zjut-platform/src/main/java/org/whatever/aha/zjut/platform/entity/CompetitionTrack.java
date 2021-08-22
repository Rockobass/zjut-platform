package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:39
 */
@Data
@AllArgsConstructor
public class CompetitionTrack {
    /**
     * comp id
     */
    int compId;
    /**
     * 赛事通道id
     */
    @TableId(type = IdType.AUTO)
    int trackId;
    /**
     * 赛事通道的名字
     */
    String trackName;
    /**
     * 赛事通道描述
     */
    String trackDesc;
}
