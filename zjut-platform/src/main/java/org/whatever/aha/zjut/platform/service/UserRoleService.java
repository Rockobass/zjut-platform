/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 22:56:10:56 下午
 */

package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.RolePermission;
import org.whatever.aha.zjut.platform.entity.UserRole;
import org.whatever.aha.zjut.platform.mapper.UserRoleMapper;

import java.lang.annotation.Retention;
import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/8 10:56 下午
 */
@RequiredArgsConstructor
public class UserRoleService {
    final UserRoleMapper userRoleMapper;

    /**
     * 增加一条userId对应的Role信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public Integer addUserRole(Integer userId, Integer roleId){
        return userRoleMapper.insert(new UserRole(userId, roleId));
    }

    /**
     * 删除一条userId对应的Role信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public Integer deleteOneUR(Integer userId, Integer roleId){
        return userRoleMapper.delete(new QueryWrapper<UserRole>().eq("role_id", roleId).eq("user_id",userId));
    }

    /**
     * 删除userId对应的所有Role信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public Integer deleteRoleByUid(Integer userId){
        return userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));
    }

    /**
     * 获取某用户ID对应的所有角色信息
     */
    public List<Object> getRoleByUid(Integer userId){
        return userRoleMapper.selectObjs(new QueryWrapper<UserRole>().select("role_id").eq("user_id", userId));
    }

}
