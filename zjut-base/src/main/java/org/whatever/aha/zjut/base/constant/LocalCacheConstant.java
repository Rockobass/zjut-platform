package org.whatever.aha.zjut.base.constant;

import org.springframework.stereotype.Component;

@Component
public class LocalCacheConstant {
    public static final String USER_MESSAGES = "u:msg:%d";
    public static final String USER_MSG_QUEUE = "u:{%d}:msg:q";

    public String getKeyUserMsgQueue(int userId) {
        return String.format(USER_MSG_QUEUE, userId);
    }
}
