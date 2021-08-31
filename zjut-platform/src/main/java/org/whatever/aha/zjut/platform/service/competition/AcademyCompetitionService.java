package org.whatever.aha.zjut.platform.service.competition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.dto.competition.AcademyCompetitionDto;
import org.whatever.aha.zjut.platform.entity.competition.AcademyCompetition;
import org.whatever.aha.zjut.platform.mapper.competition.AcademyCompetitionMapper;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/30 20:36
 */
@Service
@RequiredArgsConstructor
public class AcademyCompetitionService  {
    final AcademyCompetitionMapper academyCompetitionMapper;

    /**
     * 创建新的学院比赛信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addAcademyComp(int compId, AcademyCompetitionDto academyCompetitionDto){
        AcademyCompetition academyCompetition = new AcademyCompetition();
        BeanUtils.copyProperties(academyCompetitionDto, academyCompetition);
        academyCompetition.setCompId(compId);
        return academyCompetitionMapper.insert(academyCompetition);
    }

    /**
     * 根据List创建新的学院比赛信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addAcademyCompByList(int compId, List<AcademyCompetitionDto> academyCompetitionDtoList){
        academyCompetitionDtoList.forEach(competitionKeyPointDto->this.addAcademyComp(compId,competitionKeyPointDto));
        return academyCompetitionDtoList.size();
    }

    /**
     * 删除一条学院比赛信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delAcademyComp(int compId, int academyId){
        return academyCompetitionMapper.delete(new QueryWrapper<AcademyCompetition>().eq("comp_id",compId).eq("academy_id",academyId));
    }

    /**
     * 更新学院剩余推荐数目
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int updateRecAmt(int compId, int academyId, AcademyCompetitionDto academyCompetitionDto){
        AcademyCompetition academyCompetition = new AcademyCompetition();
        BeanUtils.copyProperties(academyCompetitionDto, academyCompetition);
        academyCompetition.setCompId(compId);
        academyCompetition.setAcademyId(academyId);
        return academyCompetitionMapper.updateById(academyCompetition);
    }
}
