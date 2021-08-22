package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.CompetitionKeyPoint;
import org.whatever.aha.zjut.platform.mapper.CompetitionKeyPointMapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:13
 */
@Service
@RequiredArgsConstructor
public class CompetitionKeyPointService {
    final CompetitionKeyPointMapper competitionKeyPointMapper;

    /**
     * 创建新的比赛关键时间节点信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompKeyPoint(int compId, String compKeyPointName, Timestamp compSchoolEndTime, boolean compNeedAlert, int compUserType){
        return competitionKeyPointMapper.insert(new CompetitionKeyPoint(compId,compKeyPointName,compSchoolEndTime,compNeedAlert,compUserType));
    }

    /**
     * 删除比赛关键时间节点信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delCompKeyPoint(int compId, String compKeyPointName){
        return competitionKeyPointMapper.delete(new QueryWrapper<CompetitionKeyPoint>().eq("comp_id",compId).eq("comp_key_point_name",compKeyPointName));
    }

    /**
     * 获取某赛事对应的所有时间点信息
     */
    public List<CompetitionKeyPoint> getCompKeyPoint(int compId){
        return competitionKeyPointMapper.selectList(new QueryWrapper<CompetitionKeyPoint>().eq("comp_id", compId).orderByAsc("comp_key_time"));
    }
}
