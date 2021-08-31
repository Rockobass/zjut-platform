package org.whatever.aha.zjut.base.constant;


import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RedisCacheConstant {
    // 用户接收消息队列
    public static final String USER_MSG_QUEUE = "u:{%d}:m:in";
    // 消息
    public static final String MSG = "msg:{%s}";
    // 用户已读消息
    public static final String USER_READ_MSG = "u:{%d}:m:read";
    // 用户未读消息
    public static final String USER_UNREAD_MSG = "u:{%d}:m:ur";
    // 用户发送消息
    public static final String USER_SENT_MSG = "u:{%d}:m:out";

    public static final List<Object> MESSAGE_OUTLINE_KEYS = Arrays.asList("sendTime", "title", "senderName");

    public String getKeyUserMsgQueue(int receiverId) {
        return String.format(USER_MSG_QUEUE, receiverId);
    }

    public String getKeyMsg(String msgId) {
        return String.format(MSG, msgId);
    }

    public String getKeyUserMsgRead(int userId) {
        return String.format(USER_READ_MSG, userId);
    }

    public String getKeyUserMsgUnRead(int userId) {
        return String.format(USER_UNREAD_MSG, userId);
    }

    public String getKeyUserMsgSent(int userId) {
        return String.format(USER_SENT_MSG, userId);
    }
}
