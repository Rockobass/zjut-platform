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
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = page * pageSize - 1;
        Object[] userMsgIds = msgUtil.getUserMsgIds(userId, start, end);
        Object[] values = msgUtil.getMsgOutline(userMsgIds);

        Map<Integer, List<Object>> cacheMap = new HashMap<>();
        ArrayList<Object> result = msgUtil.convertToOutline(userMsgIds, values);
        cacheMap.put(page, result);
        localCache.put(key, cacheMap);
    }

    @Async
    public void rmUserMessageSentCacheLocal(int userId) {

    }
}
