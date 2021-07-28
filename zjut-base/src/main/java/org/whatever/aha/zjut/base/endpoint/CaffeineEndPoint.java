package org.whatever.aha.zjut.base.endpoint;

import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Endpoint(id = "caffeine")
public class CaffeineEndPoint {
    @Autowired
    CacheManager caffeineCacheManager;

    @ReadOperation
    public Object getStats() {
        List<Object> stats = new ArrayList<>();
        caffeineCacheManager.getCacheNames().forEach(e -> {
            Map<String, Object> map = new HashMap<>();
            CacheStats stat = ((CaffeineCache) caffeineCacheManager.getCache(e)).getNativeCache().stats();
            map.put("cache_name", e);
            map.put("hit_count", stat.hitCount());
            map.put("miss_count", stat.missCount());
            map.put("load_count", stat.loadCount());
            map.put("miss_rate", stat.missRate());
            map.put("load_success_count", stat.loadSuccessCount());
            map.put("load_failure_count", stat.loadFailureCount());
            map.put("total_load_time", stat.totalLoadTime());
            map.put("request_count",stat.requestCount());
            map.put("eviction_weight", stat.evictionWeight());
            map.put("eviction_count", stat.evictionCount());
            stats.add(map);
//            stats.add(((CaffeineCache)caffeineCacheManager.getCache(e)).getNativeCache().stats().toString());
        });
        return stats;
    }

}
