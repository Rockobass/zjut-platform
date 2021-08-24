package org.whatever.aha.zjut.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.whatever.aha.zjut.platform.entity.User;

/**
 * @author Baby_mo
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select role_name from role where role_id in (select role_id from user_role where user_id = #{loginId})")
    public String[] getRoleById(String loginId);

    @Select("select permission_code from role_permission where role_id in (select role_id from user_role where user_id = #{loginId})")
    public String[] getPermissionById(String loginId);

    @Select("select login_type from user where user_id = #{userId}")
    public int getLoginTypeById(int userId);
}
