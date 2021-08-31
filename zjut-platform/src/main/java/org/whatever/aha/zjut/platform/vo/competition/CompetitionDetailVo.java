package org.whatever.aha.zjut.platform.vo.competition;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/28 9:57
 */
public class CompetitionDetailVo {
    int compId;
    int compType;
    int commpYear;
    int compTh;
    String compInfo;
    int compStatus;


    List<CompetitionStageVo> stages;    //鼠标悬停查询阶段对应小状态
    List<CompetitionTrackVo> tracks;
    List<CompetitionKeyPointVo> keyPoints;
}
