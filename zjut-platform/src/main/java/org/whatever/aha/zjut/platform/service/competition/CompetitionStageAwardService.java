package org.whatever.aha.zjut.platform.service.competition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionStageAwardDto;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageAward;
import org.whatever.aha.zjut.platform.mapper.competition.CompetitionStageAwardMapper;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/25 0:41
 */
@RequiredArgsConstructor
@Service
public class CompetitionStageAwardService {
    final CompetitionStageAwardMapper competitionStageAwardMapper;

    /**
     * 创建新的比赛阶段奖项信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompStageAward(int compId, CompetitionStageAwardDto competitionStageAwardDto){
        CompetitionStageAward competitionStageAward = new CompetitionStageAward();
        BeanUtils.copyProperties(competitionStageAwardDto, competitionStageAward);
        competitionStageAward.setCompId(compId);
        return competitionStageAwardMapper.insert(competitionStageAward);
    }

    /**
     * 根据List创建新的比赛阶段奖项信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompStageAwardByList(int compId, List<CompetitionStageAwardDto> competitionStageAwardDtoList){
        competitionStageAwardDtoList.forEach(competitionStageAwardDto->this.addCompStageAward(compId,competitionStageAwardDto));
        return competitionStageAwardDtoList.size();
    }

    /**
     * 删除比赛关键时间节点信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delCompStageAward(int compId, int stageId){
        return competitionStageAwardMapper.delete(new QueryWrapper<CompetitionStageAward>().eq("comp_id",compId).eq("stage_id",stageId));
    }
}
