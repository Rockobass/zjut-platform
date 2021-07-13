package org.whatever.aha.zjut.base.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.whatever.aha.zjut.base.constant.ErrorCode;

import java.time.Instant;
import java.util.Map;

@Getter
@Builder
@ToString
public final class ErrorDetail {
    private final ErrorCode code;
    private final String requestId;
    private final String path;
    private final Instant timestamp;
    private final Map<String, Object> data;


    public ErrorDetail(ErrorCode code, String requestId, String path, Instant timestamp, Map<String, Object> data) {
        this.code = code;
        this.requestId = requestId;
        this.path = path;
        this.timestamp = timestamp;
        this.data = data;
    }
}
