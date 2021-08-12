package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.base.exception.app.AccountBlockedException;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.mapper.UserMapper;

import java.util.Map;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "ExpireOneMin")
public class UserService {

    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;

    /**
     * 通过ID查询用户
     */
    @Cacheable(key = "'user_'+#userId")
    public User getUserById(Integer userId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
    }

    /**
     * 通过手机号或用户名查询用户
     */
    @Cacheable(key = "'user_'+#username")
    public User getUserByUsernameOrPhone(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username).or().eq("phone_number", username));
    }

    /**
     * 通过手机号查询用户是否存在
     */
    @Cacheable(key = "'not_exist_'+#phoneNumber")
    public boolean exist(String phoneNumber) {
        int count = userMapper.selectCount(new QueryWrapper<User>().eq("phone_number", phoneNumber));
        return count != 0;
    }

    /**
     * 检查账户是否过期
     */
    public void checkAccount(User user) {
        if (user.getDisabled()){
            throw new AccountBlockedException(Map.of("username", user.getUsername(), "untie_time", user.getUntieTime()));
        }
    }

    /**
     * 更新密码
     */
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {
            @CacheEvict(key = "'user_'+#user.userId"),
            @CacheEvict(key = "'user'+#user.username"),
            @CacheEvict(key = "'user'+#user.phoneNumber")}
    )
    public void resetPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userMapper.updateById(user);
    }

}
