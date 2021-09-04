package org.whatever.aha.zjut.platform.dto.competition;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/04 16:19
 */
@Data
public class CompetitionStageDetailDto {
    @Valid
    CompetitionStageDto competitionStageDto;

    @Valid
    List<CompetitionTrackDto> competitionTrackDtoList;

    @Valid
    List<CompetitionStageAwardDto> competitionStageAwardDtoList;

    @Valid
    List<AcademyCompetitionDto> academyCompetitionDtoList;

    @Valid
    List<CompetitionKeyPointDto> competitionKeyPointDtoList;
}
