/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/9
 *
 * 自定义时间格式代替DATE标签：2021-08-09 01:49:1:49 上午
 */

package org.whatever.aha.zjut.platform.Util;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.entity.UserRole;
import org.whatever.aha.zjut.platform.mapper.UserRoleMapper;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/9 1:49 上午
 */
@Component
public class UserRoleUtil {
    static UserRoleMapper userRoleMapper;
    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        UserRoleUtil.userRoleMapper = userRoleMapper;
    }

    /**
     * 获取当前User对应的Role
     */
    public static List<Object> getCurrRole() {
        Integer userId = UserUtil.getCurrUser().getUserId();
        return userRoleMapper.selectObjs(new QueryWrapper<UserRole>().select("role_id").eq("user_id", userId));
    }

}
