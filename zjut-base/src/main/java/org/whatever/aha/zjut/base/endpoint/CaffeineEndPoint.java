package org.whatever.aha.zjut.base.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Endpoint(id = "caffeine")
public class CaffeineEndPoint {
    @Autowired
    CacheManager caffeineCacheManager;

    @ReadOperation
    public Object getStats() {
        List<Object> stats = new ArrayList<>();
        caffeineCacheManager.getCacheNames().forEach(e -> {
            stats.add(((CaffeineCache)caffeineCacheManager.getCache(e)).getNativeCache().stats().toString());
        });
        return stats;
    }

}
