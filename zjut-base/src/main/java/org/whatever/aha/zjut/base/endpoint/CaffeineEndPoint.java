package org.whatever.aha.zjut.base.endpoint;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.RequiredArgsConstructor;
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

/**
 * caffeine监控端点 /actuator/caffeine
 */
@Component
@Endpoint(id = "caffeine")
@RequiredArgsConstructor
public class CaffeineEndPoint {

    final CacheManager caffeineCacheManager;

    @ReadOperation
    public Object getStats() {
        List<Object> stats = new ArrayList<>();
        caffeineCacheManager.getCacheNames().forEach(e -> {
            Map<String, Object> map = new HashMap<>();
            Cache<Object, Object> nativeCache = ((CaffeineCache) caffeineCacheManager.getCache(e)).getNativeCache();
            CacheStats stat = nativeCache.stats();
            map.put("estimated_size", nativeCache.estimatedSize());
            map.put("cache_name", e);
            map.put("hit_count", stat.hitCount());
            map.put("miss_count", stat.missCount());
            map.put("load_count", stat.loadCount());
            map.put("miss_rate", stat.missRate());
            map.put("load_success_count", stat.loadSuccessCount());
            map.put("load_failure_count", stat.loadFailureCount());
            map.put("total_load_time", stat.totalLoadTime());
            map.put("request_count", stat.requestCount());
            map.put("eviction_weight", stat.evictionWeight());
            map.put("eviction_count", stat.evictionCount());
            stats.add(map);
        });
        return stats;
    }

}
