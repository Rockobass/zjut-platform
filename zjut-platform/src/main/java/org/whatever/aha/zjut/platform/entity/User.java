package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Baby_mo
 */
@Data
@Builder
public class User {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    int userId;
    /**
     * 用户名
     */
    String username;
    /**
     * 电话号码
     */
    String phoneNumber;
    /**
     * 密码
     */
    String password;
    /**
     * 是否封禁
     */
    Boolean disabled;
    /**
     * 登录类型
     */
    int loginType;
    /**
     * 解封时间
     */
    Date untieTime;
}
