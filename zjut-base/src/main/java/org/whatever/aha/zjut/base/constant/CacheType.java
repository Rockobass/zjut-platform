package org.whatever.aha.zjut.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缓存枚举类
 * @author Baby_mo
 */
@Getter
@AllArgsConstructor
public enum CacheType {
    NoExpire(100, 3000, false,-1),
    ExpireOneMin(100, 1000, true, 60),
    Captcha(100, 10000, true, 60),
    SMS(100, 10000, true, 300),
    Roles(100, 3000, false, -1);

    private final int initialCapacity;
    private final int maximumSize;
    private final boolean expires;
    private final int expireTime;
}
