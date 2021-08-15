package org.whatever.aha.zjut.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.Major;

/**
 * @author Baby_mo
 */
public interface MajorMapper extends BaseMapper<Major> {

    @Select("select major_name from major where major_id = #{id}")
    String getMajorName(int id);
}
