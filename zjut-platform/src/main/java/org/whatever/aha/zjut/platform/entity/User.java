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
    @TableId(type = IdType.AUTO)
    int userId;
    String username;
    String phoneNumber;
    String password;
    Boolean disabled;
    int loginType;
    Date untieTime;
}
