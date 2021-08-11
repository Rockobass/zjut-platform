package org.whatever.aha.zjut.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.whatever.aha.zjut.platform.entity.UserRole;

/**
 * @author Vc
 * @version 1.0
 * @date 2021/8/8 10:55 下午
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Insert("insert into user_role(role_id, user_id) value(1, #{user_id})")
    void addStudentRole(int userId);
}
