package org.whatever.aha.zjut.platform.dto.competition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionTrack;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 22:20
 */
@Data
@ApiModel("竞赛通道信息Dto")
public class CompetitionTrackDto {
    /**
     * 赛事通道的名字
     */
    @ApiModelProperty("赛事通道的名字")
    String trackName;
    /**
     * 赛事通道描述
     */
    @ApiModelProperty("赛事通道描述")
    String trackDesc;

    public static CompetitionTrack build(CompetitionTrackDto dto) {
        return CompetitionTrack.builder()
                .trackDesc(dto.trackDesc)
                .trackName(dto.trackName)
                .build();
    }
}
