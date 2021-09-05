package org.whatever.aha.zjut.platform.mapper.competition;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.platform.entity.competition.Competition;
import org.whatever.aha.zjut.platform.vo.competition.CompetitionRoughVo;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/14 11:09
 */
public interface CompetitionMapper extends BaseMapper<Competition> {
    @Update("update competition set comp_status = #{compStatus} where comp_id = #{compId}")
    int setCompStatus(@Param("compStatus") int compStatus, @Param("compId") int compId);

    @Select("select * from Competition where comp_type=#{compType} order by comp_th DESC limit #{offset}, #{pageSize}")
    @Results({
            @Result(column="comp_id",property="compId"),
            @Result(column="comp_id",property="keyPoints",
                    many=@Many(
                            select="org.whatever.aha.zjut.platform.mapper.competition.CompetitionKeyPointMapper.getCompKeyPointVo"
                    )
            )
    })
    List<CompetitionRoughVo> getCompetitionRoughInfoPageable(@Param("compType") int compType, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}
