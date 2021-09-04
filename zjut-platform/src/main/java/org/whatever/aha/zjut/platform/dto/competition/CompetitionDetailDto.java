package org.whatever.aha.zjut.platform.dto.competition;

import lombok.Data;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionKeyPointDto;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionStageStatusDto;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionTrackDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/27 2:18
 */
@Data
public class CompetitionDetailDto {
    @Valid
    CompetitionDto competitionDto;

    @Valid
    List<CompetitionStageDetailDto> competitionStageDetailDtoList;

}
