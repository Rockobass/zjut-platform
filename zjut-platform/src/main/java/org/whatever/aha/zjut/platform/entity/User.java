package org.whatever.aha.zjut.platform.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    Integer id;
    String username;
    String phoneNumber;
    Boolean disabled;
    Date untieTime;
}
