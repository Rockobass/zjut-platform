package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.CompetitionStageStatus;
import org.whatever.aha.zjut.platform.mapper.CompetitionStageStatusMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:43
 */
@RequiredArgsConstructor
@Service
public class CompetitionStageStatusService {
    final CompetitionStageStatusMapper competitionStageStatusMapper;

    /**
     * 创建新的赛事小阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompStageStatus(int compStageId, String compStageStatusName, int compStageStatusOrder, Timestamp compStageStausEndTime,String compStageStausDesc){
        return competitionStageStatusMapper.insert(new CompetitionStageStatus(compStageId, null, compStageStatusName, compStageStatusOrder, compStageStausEndTime, compStageStausDesc));
    }

    /**
     * 批量创建新的赛事小阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompStageStatusInBatch(List<Integer> compStageId, List<String> compStageStatusName, List<Integer> compStageStatusOrder, List<Timestamp> compStageStausEndTime, List<String> compStageStausDesc){
        List<CompetitionStageStatus> cs = Collections.synchronizedList(new ArrayList<CompetitionStageStatus>());
        for (int j = 0; j < compStageId.size(); j++){
            cs.add(new CompetitionStageStatus(compStageId.get(j),null,compStageStatusName.get(j),compStageStatusOrder.get(j),compStageStausEndTime.get(j), compStageStausDesc.get(j)));
        }
        return competitionStageStatusMapper.insertBatchSomeColumn(cs);
    }

    /**
     * 删除一条赛事小阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delCompStageStatus(int compStageId, int statusId){
        return competitionStageStatusMapper.delete(new QueryWrapper<CompetitionStageStatus>().eq("stage_id", compStageId).eq("status_id",statusId));
    }

    /**
     * 获取当前阶段下一个小阶段
     */
    public int getNextStage(int compId, int compStageId, int curStageOrder){
        int fianlOrder = competitionStageStatusMapper.getFinalOrder(compId,compStageId);
        return fianlOrder> curStageOrder? curStageOrder+1: fianlOrder;
    }
}
