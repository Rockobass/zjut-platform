package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;
import org.whatever.aha.zjut.base.exception.AppException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Baby_mo
 */
@Component
@RequiredArgsConstructor
public class MessageUtil {
    final RedisTemplate<String, Object> redisTemplate;
    final RedisCacheConstant redisConstant;


    /**
     * type有三种情况
     * 0 获取用户消息队列
     * 1 获取已发送队列
     * 2 获取已读队列
     */
    public Object[] getMsgIds(int userId, int start, int end, int type) {
        String key;
        switch (type) {
            case 0 : key = redisConstant.getKeyUserMsgQueue(userId);break;
            case 1 : key = redisConstant.getKeyUserMsgSent(userId);break;
            case 2 : key = redisConstant.getKeyUserMsgRead(userId);break;
            default: throw new AppException(ErrorCode.ILLEGAL_REQUEST);
        }

        return redisTemplate.opsForList().range(key, start, end).toArray();
    }

    // 传入msgId数组，pipeline批量获取结果
    public Object[] getMsgOutlineInRedis(Object[] msgIds) {
        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            Arrays.stream(msgIds).forEach(e -> {
                connection.hMGet(redisConstant.getKeyMsg((String) e).getBytes(), "sendTime".getBytes(), "title".getBytes(), "senderName".getBytes());
            });
            return null;
        }).toArray();
    }

    public ArrayList<Object> convertToOutline(Object[] userMsgIds, Object[] values) {
        ArrayList<Object> result = new ArrayList<>();
        for (int i = 0; i < userMsgIds.length; i++) {
            List<Object> value = (List<Object>) values[i];
            result.add(Map.of("msgId", userMsgIds[i], "sendTime", value.get(0), "title", value.get(1), "senderName", value.get(2)));
        }
        return result;
    }

    public ArrayList<Object> getMsgOutline(int userId, int page, int type) {
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = page * pageSize - 1;
        Object[] userMsgIds = getMsgIds(userId, start, end, type);
        Object[] values = getMsgOutlineInRedis(userMsgIds);
        return convertToOutline(userMsgIds, values);
    }
}
