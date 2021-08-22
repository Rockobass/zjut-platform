package org.whatever.aha.zjut.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.CompetitionStage;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/23 2:15
 */
public interface CompetitionStageMapper extends EasyBaseMapper<CompetitionStage> {
    @Select("select stage_order from competition_stage where comp_id = #{comp_id} and stage_id = #{stage_id} order by stage_order DESC limit 1")
    int getFinalOrder(@Param("comp_id")int comp_id, @Param("stage_id")int stage_id);
}
