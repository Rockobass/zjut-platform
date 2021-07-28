package org.whatever.aha.zjut.base.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.whatever.aha.zjut.base.constant.CacheType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {
    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caffeineCaches = new ArrayList<>();

        for (CacheType cacheType : CacheType.values()) {
            caffeineCaches.add(new CaffeineCache(cacheType.name(),
                    Caffeine.newBuilder()
                            .initialCapacity(cacheType.getInitialCapacity())
                            .maximumSize(cacheType.getMaximumSize())
                            .expireAfterWrite(cacheType.getExpires(), TimeUnit.SECONDS)
                            .recordStats()
                            .build()));
        }

        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }
}
