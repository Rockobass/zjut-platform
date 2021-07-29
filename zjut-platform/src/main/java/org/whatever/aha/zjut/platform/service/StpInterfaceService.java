package org.whatever.aha.zjut.platform.service;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.platform.mapper.UserMapper;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * Sa-Token的自定义权限验证扩展
 */
@Service
public class StpInterfaceService implements StpInterface {

    @Resource
    UserMapper userMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Cacheable(value = "Roles", key = "'role_'+#loginId")
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String[] roles = userMapper.getRoleById((String)loginId);
        return Arrays.asList(roles);
    }
}