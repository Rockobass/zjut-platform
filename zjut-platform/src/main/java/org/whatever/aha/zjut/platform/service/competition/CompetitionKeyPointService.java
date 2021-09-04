package org.whatever.aha.zjut.platform.service.competition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionKeyPointDto;
import org.whatever.aha.zjut.platform.entity.competition.AcademyCompetition;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionKeyPoint;
import org.whatever.aha.zjut.platform.mapper.competition.CompetitionKeyPointMapper;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionKeyPointVo;

import java.util.Date;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:13
 */
@RequiredArgsConstructor
@Service
public class CompetitionKeyPointService {
    final CompetitionKeyPointMapper competitionKeyPointMapper;

    /**
     * 创建新的比赛关键时间节点信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompKeyPoint(int compId, int stageId, CompetitionKeyPointDto competitionKeyPointDto){
        CompetitionKeyPoint competitionKeyPoint = new CompetitionKeyPoint();
        BeanUtils.copyProperties(competitionKeyPointDto, competitionKeyPoint);
        competitionKeyPoint.setCompId(compId);
        competitionKeyPoint.setStageId(stageId);
        return competitionKeyPointMapper.insert(competitionKeyPoint);
    }

    /**
     * 根据List创建新的比赛关键时间节点信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompKeyPointByList(int compId, int stageId, List<CompetitionKeyPointDto> competitionKeyPointDtoList){
        competitionKeyPointDtoList.forEach(competitionKeyPointDto->this.addCompKeyPoint(compId, stageId, competitionKeyPointDto));
        return competitionKeyPointDtoList.size();
    }

    /**
     * 删除比赛关键时间节点信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delCompKeyPoint(int compId, int stageId, String compKeyPointName){
        return competitionKeyPointMapper.delete(new QueryWrapper<CompetitionKeyPoint>().eq("comp_id",compId).eq("stage_id",stageId).eq("comp_key_point_name",compKeyPointName));
    }

    /**
     * 获取某赛事阶段对应的所有时间点信息
     */
    public List<CompetitionKeyPointVo> getCompKeyPointVo(int compId, int stageId){
        return competitionKeyPointMapper.getCompKeyPointVo(compId,stageId);
    }


}
