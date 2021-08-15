package org.whatever.aha.zjut.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.Academy;

/**
 * @author Baby_mo
 */
public interface AcademyMapper extends BaseMapper<Academy> {

    @Select("select academy_name from academy where academy_id = #{id}")
    String getAcademyName(int id);
}
