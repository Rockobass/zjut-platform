package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.LocalCacheConstant;
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
    final RedisCacheConstant redisConstant;
    final LocalCacheConstant cacheConstant;
    final MessageUtil msgUtil;
    Cache localCache;

    @PostConstruct
    void init() {
        localCache = caffeineCacheManager.getCache("Message");
    }


    public void createUserMessage(int senderId, int receiverId, Timestamp sendTime, String title, String content, String senderName) {
        String mid = UUID.randomUUID().toString().substring(0, 12);
        // 用户消息队列
        String key1 = redisConstant.getKeyUserMsgQueue(receiverId);
        // 消息
        String key2 = redisConstant.getKeyMsg(mid);
        // 用户已读消息
        String key3 = redisConstant.getKeyUserMsgRead(receiverId);
        // 用户发送队列
        String key4 = redisConstant.getKeyUserMsgSent(senderId);

        List<String> keys = Arrays.asList(key1, key2, key3, key4);
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/createMsg.lua")));

        redisTemplate.execute(redisScript, keys, mid, senderId, receiverId, sendTime, title, content, senderName);
        localCache.evict(cacheConstant.getKeyUserMsgQueue(receiverId));
        asyncMethod.loadMsgQueuePage(receiverId, 1);
    }



    public Object getUserMessages(int userId, int page) {
        Map<Integer, List<Object>> cacheMap;
        String key = cacheConstant.getKeyUserMsgQueue(userId);
        Cache.ValueWrapper valueWrapper = localCache.get(key);
        if (valueWrapper == null) {
            cacheMap = new HashMap<>();
        } else {
            cacheMap = (Map<Integer, List<Object>>) valueWrapper.get();
        }

        if (cacheMap.containsKey(page)) {
            return cacheMap.get(page);
        }

        // TODO 异步再加载一页
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = page * pageSize - 1;
        Object[] userMsgIds = msgUtil.getUserMsgIds(userId, start, end);
        Object[] values = msgUtil.getMsgOutline(userMsgIds);
        ArrayList<Object> result = msgUtil.convertToOutline(userMsgIds, values);

        cacheMap.put(page, result);
        localCache.put(key, cacheMap);
        return result;
    }

}
