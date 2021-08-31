package org.whatever.aha.zjut.platform.service.competition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionDetailDto;
import org.whatever.aha.zjut.platform.dto.competition.CompetitionDto;
import org.whatever.aha.zjut.platform.entity.competition.AcademyCompetition;
import org.whatever.aha.zjut.platform.entity.competition.Competition;
import org.whatever.aha.zjut.platform.mapper.competition.CompetitionMapper;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionRoughVo;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/14 11:10
 */
@Service
@RequiredArgsConstructor
public class CompetitionService {
    final CompetitionMapper competitionMapper;
    final CompetitionKeyPointService competitionKeyPointService;
    final CompetitionTrackService competitionTrackService;
    final CompetitionStageService competitionStageService;
    final CompetitionStageAwardService competitionStageAwardService;
    final CompetitionStageStatusService competitionStageStatusService;
    final AcademyCompetitionService academyCompetitionService;


    /**
     * 创建新的比赛详细信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompDetail(int compId, CompetitionDetailDto competitionDetailDto){
        competitionKeyPointService.addCompKeyPointByList(compId, competitionDetailDto.getCompetitionKeyPointDtoList());
        academyCompetitionService.addAcademyCompByList(compId, competitionDetailDto.getAcademyCompetitionDtoList());
        competitionStageStatusService.addCompStageStatusByList(competitionDetailDto.getCompetitionStageStatusDtoList());
        competitionStageAwardService.addCompStageAwardByList(compId, competitionDetailDto.getCompetitionStageAwardDtoList());
        competitionStageService.addCompStageByList(compId, competitionDetailDto.getCompetitionStageDtoList());
        competitionTrackService.addTrackByList(compId, competitionDetailDto.getCompetitionTrackDtoList());
        return this.addCompInfo(competitionDetailDto.getCompetitionDto());
    }

    /**
     * 根据比赛类型分页获取比赛粗略信息
     */
    public List<CompetitionRoughVo> getCompetitionRoughPageable(int compType, Page<CompetitionRoughVo> page){
//        IPage<CompetitionRoughVo> compRoughVoIPage = competitionMapper.selectPage(page, new QueryWrapper<CompetitionRoughVo>().eq("comp_type",compType).orderByDesc("comp_th"));
        //return compRoughVoIPage.getRecords();
        return null;
    }


    /**
     * 创建新的比赛单体信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompInfo(CompetitionDto competitionDto){
        Competition comp = new Competition();
        BeanUtils.copyProperties(competitionDto, comp);
        return competitionMapper.insert(comp);
    }

    /**
     * 赛事进入下一个状态
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int enterNextStatus(int compId){
        int curStatus = competitionMapper.selectById(compId).getCompStatus();
        return competitionMapper.setCompStatus(curStatus < 3 ? curStatus+1 : 3, compId);
    }

    /**
     * 获取当前赛事最新届数
     */
    public int getNewTh(int compId){
        return competitionMapper.selectOne(new QueryWrapper<Competition>().eq("comp_id",compId).orderByDesc("comp_th").last("limit 1")).getCompTh() + 1;
    }



}
