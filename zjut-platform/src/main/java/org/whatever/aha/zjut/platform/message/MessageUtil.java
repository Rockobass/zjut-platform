package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;

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


    public Object[] getUserMsgIds(int userId, int start, int end) {
        String key = String.format(RedisCacheConstant.USER_MSG_QUEUE, userId);
        return redisTemplate.opsForList().range(key, start, end).toArray();
    }

    // 传入msgId数组，pipeline批量获取结果
    public Object[] getMsgOutline(Object[] msgIds) {
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
}
