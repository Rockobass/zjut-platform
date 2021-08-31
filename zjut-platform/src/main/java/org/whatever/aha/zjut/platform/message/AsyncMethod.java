package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.LocalCacheConstant;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
public class AsyncMethod {
    final CacheManager caffeineCacheManager;
    final MessageUtil msgUtil;
    final LocalCacheConstant cacheConstant;
    Cache localCache;

    @PostConstruct
    void init() {
        localCache = caffeineCacheManager.getCache("Message");
    }
    @Async
    public void loadMsgQueuePage(int userId, int page) {
        String key = cacheConstant.getKeyUserMsgQueue(userId);

        Map<Integer, List<Object>> cacheMap = new HashMap<>();
        ArrayList<Object> result = msgUtil.getMsgOutline(userId, page, 0);
        cacheMap.put(page, result);
        localCache.put(key, cacheMap);
    }

}
