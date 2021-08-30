package org.whatever.aha.zjut.platform.mapper.competition;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionStaticTags;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:23
 */
public interface CompetitionStaticTagsMapper extends BaseMapper<CompetitionStaticTags> {

    @Delete("<script> "+
            "delete from competition_static_tags where comp_type = #{type} and comp_group = #{group} and comp_tag in" +
            "<foreach collection='tags' item='tag' open='(' separator=',' close=')'>#{tag}</foreach>" +
            "</script>")
    public int deleteTagInBatch(@Param("type") int compType,@Param("group") String compGroup,@Param("tags") String[] compTags);


    @Select("select distinct comp_group from competition_static_tags where comp_type = #{type}")
    public List<String> getGroupsByType(@Param("type") int compType);

}
