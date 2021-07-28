package org.whatever.aha.zjut.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {
    common(100, 500, 30);

    private final int initialCapacity;
    private final int maximumSize;
    private final int expires;
}
