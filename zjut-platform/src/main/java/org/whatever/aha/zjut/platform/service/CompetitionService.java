package org.whatever.aha.zjut.platform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.Competition;
import org.whatever.aha.zjut.platform.entity.UserRole;
import org.whatever.aha.zjut.platform.mapper.CompetitionMapper;

import java.sql.Timestamp;

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

    /**
     * 创建新的比赛信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompInfo(int compType, int compYear, int compSchoolRecAmt, String compInfo, Boolean compJudgeWay, String compJudgeStandard){
        return competitionMapper.insert(new Competition(null, compType, compYear, compSchoolRecAmt, compInfo, compJudgeWay, compJudgeStandard));
    }

}
