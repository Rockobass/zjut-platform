/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/9
 *
 * 自定义时间格式代替DATE标签：2021-08-09 01:55:1:55 上午
 */

package org.whatever.aha.zjut.platform.Util;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.mapper.UserMapper;
import org.whatever.aha.zjut.platform.mapper.UserRoleMapper;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/9 1:55 上午
 */
@Component
public class UserUtil {
    static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        UserUtil.userMapper = userMapper;
    }

    /**
     * 获取当前User
     */
    public static User getCurrUser() {
        long userId = StpUtil.getLoginIdAsLong();
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
    }

}
