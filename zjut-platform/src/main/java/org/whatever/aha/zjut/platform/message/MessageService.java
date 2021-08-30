package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "Message")
public class MessageService {
    final RedisTemplate<String, Object> redisTemplate;
    final CacheManager caffeineCacheManager;
    final AsyncMethod asyncMethod;
    Cache localCache;

    @PostConstruct
    void init() {
        localCache = caffeineCacheManager.getCache("Message");
    }


    public Object[] getUserMsgIds(int userId, int start, int end) {
        String key = String.format(RedisCacheConstant.USER_MSG_QUEUE, userId);
        return redisTemplate.opsForList().range(key, start, end).toArray();
    }

    public void createUserMessage(int senderId, int receiverId, Timestamp sendTime, String title, String content, String senderName) {
        String mid = UUID.randomUUID().toString().substring(0, 12);
        // 用户消息队列
        String key1 = String.format(RedisCacheConstant.USER_MSG_QUEUE, receiverId);
        // 消息
        String key2 = String.format(RedisCacheConstant.MSG, mid);
        // 用户已读消息
        String key3 = String.format(RedisCacheConstant.USER_READ_MSG, receiverId);
        // 用户发送队列
        String key4 = String.format(RedisCacheConstant.USER_SENT_MSG, senderId);

        List<String> keys = Arrays.asList(key1, key2, key3, key4);
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/createMsg.lua")));

        redisTemplate.execute(redisScript, keys, mid, senderId, receiverId, sendTime, title, content, senderName);
        asyncMethod.rmUserMessageCacheLocal(receiverId);
    }

    @Cacheable(key = "'u:'+#userId+':page:'+#page")
    public Object getUserMessages(int userId, int page) {
        // 最多存储一百条消息
        if (page > 9) {
            return null;
        }

        int pageSize = 10;
        // TODO 异步再加载一页

        int start = (page - 1) * pageSize;
        int end = page * pageSize - 1;
        Object[] userMsgIds = getUserMsgIds(userId, start, end);
        Object[] values = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            Arrays.stream(userMsgIds).forEach(e -> {
                connection.hMGet(String.format(RedisCacheConstant.MSG, e).getBytes(), "sendTime".getBytes(), "title".getBytes(), "senderName".getBytes());
            });
            return null;
        }).toArray();

        ArrayList<Object> result = new ArrayList<>();
        for (int i = 0; i < userMsgIds.length; i++) {
            List<Object> value = (List<Object>) values[i];
            result.add(Map.of("msgId", userMsgIds[i], "sendTime", value.get(0), "title", value.get(1), "senderName", value.get(2)));
        }
        return result;
    }

}
