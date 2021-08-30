package org.whatever.aha.zjut.platform.mapper.competition;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStageStatus;
import org.whatever.aha.zjut.platform.mapper.EasyBaseMapper;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 3:41
 */
public interface CompetitionStageStatusMapper extends EasyBaseMapper<CompetitionStageStatus> {
    @Select("select status_order from competition_stage_status where stage_id = #{stage_id} and status_id = #{status_id} order by status_order DESC limit 1")
    int getFinalOrder(@Param("stage_id")int stage_id, @Param("status_id")int status_id);
}
