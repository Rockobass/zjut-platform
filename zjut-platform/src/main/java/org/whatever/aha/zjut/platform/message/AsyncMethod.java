package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.LocalCacheConstant;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
public class AsyncMethod {
    final CacheManager caffeineCacheManager;
    final MessageUtil msgUtil;
    final LocalCacheConstant cacheConstant;
    final RedisCacheConstant redisConstant;
    final RedisTemplate<String, Object> redisTemplate;
    Cache localCache;

    @PostConstruct
    void init() {
        localCache = caffeineCacheManager.getCache("Message");
    }

    /**
     *  TODO 未重载用户发送队列的缓存
     */
    @Async
    public void loadMsgQueuePage(int userId, int page) {
        String key = cacheConstant.getKeyUserMsgQueue(userId);

        Map<Integer, List<Object>> cacheMap = new HashMap<>();
        List<Object> result = msgUtil.getMsgOutline(userId, page, 0);
        cacheMap.put(page, result);
        localCache.put(key, cacheMap);
    }

    @Async
    public void readMessage(int userId, String mid) {
        String key2 = redisConstant.getKeyUserMsgUnRead(userId);
        // count等于1表示消息未读
        Long count = redisTemplate.opsForList().remove(key2, 1, mid);
        if (count <= 0) {
            return;
        }

        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String readTime = s.format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());

        String key0 = redisConstant.getKeyMsg(mid);
        redisTemplate.opsForHash().put(key0, "readTime", readTime);

        String key1 = redisConstant.getKeyUserMsgRead(userId);
        redisTemplate.opsForHash().put(key1, mid, readTime);

        String key = cacheConstant.getKeyUserMsgQueue(userId);
        localCache.evict(key);

        refreshMsgCountLocalCache(redisConstant.getKeyUserMsgUnReadCount(userId), -1L);
    }

    /**
     * 更新本地缓存中队列的长度
     */
    public void refreshMsgCountLocalCache(String key, Long num) {
        Cache.ValueWrapper valueWrapper = localCache.get(key);
        if (valueWrapper != null) {
            Long count = (Long) valueWrapper.get();
            count += num;
            localCache.put(key, count);
        }
    }
}
