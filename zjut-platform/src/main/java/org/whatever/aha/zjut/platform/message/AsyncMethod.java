package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AsyncMethod {
    final CacheManager caffeineCacheManager;
    Cache localCache;

    @PostConstruct
    void init() {
        localCache = caffeineCacheManager.getCache("Message");
    }
    @Async
    public void rmUserMessageCacheLocal(int userId) {
        for (int i = 0; i < 10; i++) {
            localCache.evict(String.format("u:%d:page:%d", userId, i));
        }
    }

    @Async
    public void rmUserMessageSentCacheLocal(int userId) {

    }
}
