package org.whatever.aha.zjut.base.constant;

import org.springframework.stereotype.Component;

@Component
public class LocalCacheConstant {
    public static final String USER_MESSAGES = "u:msg:%d";
    public static final String USER_MSG_QUEUE = "u:{%d}:msg:q";
    public static final String USER_MSG_SENT = "u:{%d}:msg:st";

    public String getKeyUserMsgQueue(int userId) {
        return String.format(USER_MSG_QUEUE, userId);
    }

    public String getKeyUserMsgSent(int userId) {
        return String.format(USER_MSG_SENT, userId);
    }
}
