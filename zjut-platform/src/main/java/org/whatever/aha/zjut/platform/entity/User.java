package org.whatever.aha.zjut.platform.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author GuiYi Yang
 */
@Data
public class User {
    int userId;
    String username;
    String phoneNumber;
    String password;
    Boolean disabled;
    int loginType;
    Date untieTime;
}
