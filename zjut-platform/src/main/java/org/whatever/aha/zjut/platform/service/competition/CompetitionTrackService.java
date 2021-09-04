package org.whatever.aha.zjut.platform.service.competition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionTrackDto;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageStatus;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionTrack;
import org.whatever.aha.zjut.platform.mapper.competition.CompetitionTrackMapper;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/24 1:18
 */
@Service
@RequiredArgsConstructor
public class CompetitionTrackService {
    final CompetitionTrackMapper competitionTrackMapper;

    /**
     * 创建新的静态比赛映射信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addTrack(int compId, int stageId, CompetitionTrackDto competitionTrackDto){
        CompetitionTrack competitionTrack = new CompetitionTrack();
        BeanUtils.copyProperties(competitionTrackDto,competitionTrack);
        competitionTrack.setCompId(compId);
        competitionTrack.setStageId(stageId);
        return competitionTrackMapper.insert(competitionTrack);
    }

    /**
     * 根据List创建新的赛事小阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addTrackByList(int compId, int stageId, List<CompetitionTrackDto> CompetitionTrackDtoList){
        CompetitionTrackDtoList.forEach(competitionTrackDto->this.addTrack(compId,stageId,competitionTrackDto));
        return CompetitionTrackDtoList.size();
    }

    /**
     * 删除一条tag信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delTrack(int compId, int stageId, String trackName){
        return competitionTrackMapper.delete(new QueryWrapper<CompetitionTrack>().eq("comp_id", compId).eq("stage_id", stageId).eq("track_name",trackName));
    }
}
