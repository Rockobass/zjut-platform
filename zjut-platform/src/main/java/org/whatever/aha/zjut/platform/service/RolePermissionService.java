/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 16:26:4:26 下午
 */

package org.whatever.aha.zjut.platform.service;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/8 4:26 下午
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.RolePermission;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.mapper.RolePermissionMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolePermissionService {
    final RolePermissionMapper rolePermissionMapper;

    /**
     * 获取指定角色的所有权限码 【增加缓存】
     */
    @Cacheable(value="api_pcode_list", key="#roleId")
    public List<Object> getPcodeByRid(Integer roleId){
        return rolePermissionMapper.selectObjs(new QueryWrapper<RolePermission>().select("permission_code").eq("roleId", roleId));
    }

    /**
     * 获取指定角色的所有权限码 (Object类型)  【增加缓存】
     */
    @Cacheable(value="api_pcode_list2", key="#roleId")
    public List<Object> getPcodeByRid2(Integer roleId){
        List<Object> codeList = rolePermissionMapper.selectObjs(new QueryWrapper<RolePermission>().select("permission_code").eq("role_id", roleId));
        return codeList.stream().map(String::valueOf).collect(Collectors.toList());
    }

    /**
     * [T] 修改角色的一组权限关系	【清除缓存 】
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value= {"api_pcode_list", "api_pcode_list2"}, key="#roleId")
    public Integer updateRoleMenu(Integer roleId, String[] pcodeArray){
        // 万一为空
        if(pcodeArray == null){
            pcodeArray = new String[0];
        }

        // 先删除roleId
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        // 再添加
        for(String pcode : pcodeArray){
            rolePermissionMapper.insert(new RolePermission(roleId, pcode));
        }
        // 返回
        return pcodeArray.length;
    }
}
