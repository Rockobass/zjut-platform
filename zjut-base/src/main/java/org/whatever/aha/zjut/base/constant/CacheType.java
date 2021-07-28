package org.whatever.aha.zjut.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {
    NoExpire(100, 3000, false,-1),
    Captcha(100, 10000, true, 60),
    SMS(100, 10000, true, 60);

    private final int initialCapacity;
    private final int maximumSize;
    private final boolean expires;
    private final int expireTime;
}
