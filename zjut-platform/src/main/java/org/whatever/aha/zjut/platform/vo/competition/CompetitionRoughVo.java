package org.whatever.aha.zjut.platform.vo.competition;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/27 1:32
 */
public class CompetitionRoughVo {
    /**
     * 赛事 id
     */
    int compId;
    /**
     * 赛事类型
     */
    int compType;
    /**
     * 赛事举办年份
     */
    int commpYear;
    /**
     * 赛事届数
     */
    int compTh;
    /**
     * 赛事状态
     */
    int compStatus;

    /**
     * 赛事关键点（根据当前用户获得有权限的进行提示）
     */
    List<CompetitionKeyPointVo> keyPoints;
}
