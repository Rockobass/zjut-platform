package org.whatever.aha.zjut.platform.mapper.competition;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionKeyPoint;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionKeyPointVo;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:12
 */

public interface CompetitionKeyPointMapper extends BaseMapper<CompetitionKeyPoint> {
    @Select("select * from competition_key_point where comp_id = #{comp_id}")
    List<CompetitionKeyPointVo> getCompKeyPointVo(@Param("comp_id")int compId);
}
