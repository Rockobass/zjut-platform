package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.CompetitionStage;
import org.whatever.aha.zjut.platform.mapper.CompetitionStageMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 2:16
 */
@RequiredArgsConstructor
@Service
public class CompetitionStageService {
    final CompetitionStageMapper competitionStageMapper;

    /**
     * 创建新的赛事阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompStage(int compId, String compStageName, int nextStageNum, String compStageDesc, int stageOrder){
        return competitionStageMapper.insert(new CompetitionStage(compId,null,compStageName,nextStageNum,compStageDesc,stageOrder));
    }

    /**
     * 批量创建新的赛事阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompStageInBatch(List<Integer> compId, List<String> compStageName, List<Integer> nextStageNum, List<String> compStageDesc, List<Integer> stageOrder){
        List<CompetitionStage> cs = Collections.synchronizedList(new ArrayList<CompetitionStage>());
        for (int j = 0; j < compId.size(); j++){
            cs.add(new CompetitionStage(compId.get(j),null,compStageName.get(j),nextStageNum.get(j),compStageDesc.get(j),stageOrder.get(j)));
        }
        return competitionStageMapper.insertBatchSomeColumn(cs);
    }

    /**
     * 删除一条赛事阶段信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delCompStage(int compId, int compStageId){
        return competitionStageMapper.delete(new QueryWrapper<CompetitionStage>().eq("comp_id", compId).eq("comp_stage_id",compStageId));
    }

    /**
     * 获取当前阶段下一个阶段
     */
    public int getNextStage(int compId, int compStageId, int curStageOrder){
        int fianlOrder = competitionStageMapper.getFinalOrder(compId,compStageId);
        return fianlOrder> curStageOrder? curStageOrder+1: fianlOrder;
    }
}
