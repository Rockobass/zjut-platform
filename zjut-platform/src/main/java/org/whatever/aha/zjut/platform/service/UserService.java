package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.base.exception.app.AccountBlockedException;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
public class UserService {

    final UserMapper userMapper;
    final PasswordEncoder passwordEncoder;

    /**
     * 通过手机号或用户名查询用户
     */
    @Cacheable(value = "ExpireOneMin", key = "'user_'+#username")
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username).or().eq("phone_number", username));
    }

    /**
     * 通过手机号查询用户是否存在
     */
    @Cacheable(value = "ExpireOneMin", key = "'not_exist_'+#phoneNumber")
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
     * 学生注册
     */
    @Transactional
    public Integer insertStudent(String phoneNumber, String password) {
        User user = User.builder().phoneNumber(phoneNumber)
                .password(passwordEncoder.encode(password))
                .loginType(0).build();
        userMapper.insert(user);
        return user.getUserId();
    }

}
