package org.whatever.aha.zjut.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Baby_mo
 */
@Data
@Component
@ConfigurationProperties("tencent-sms")
public class SMSConfig {
    private Integer appId;

    private String appKey;

    private Integer templateId;

    private String signName;

    private Integer expireTime;

    private String region;
}
