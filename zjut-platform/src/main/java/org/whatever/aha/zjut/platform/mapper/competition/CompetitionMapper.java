package org.whatever.aha.zjut.platform.mapper.competition;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.whatever.aha.zjut.platform.entity.competition.Competition;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/14 11:09
 */
public interface CompetitionMapper extends BaseMapper<Competition> {
    @Update("update competition set comp_status = #{compStatus} where comp_id = #{compId}")
    int setCompStatus(@Param("compStatus") int compStatus, @Param("compId") int compId);


}
