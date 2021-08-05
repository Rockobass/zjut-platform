package org.whatever.aha.zjut.platform.service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.config.SMSConfig;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;
import java.util.Random;

/**
 * @author Baby_mo
 */
@Service
public class SMSService {

    final SMSConfig smsConfig;
    final CacheManager caffeineCacheManager;
    final Cache smsCache;

    public SMSService(SMSConfig smsConfig, CacheManager caffeineCacheManager) {
        this.smsConfig = smsConfig;
        this.caffeineCacheManager = caffeineCacheManager;
        this.smsCache = caffeineCacheManager.getCache("SMS");
    }

    public void sendMessage(String phoneNumber, String usage) {
        Cache.ValueWrapper valueWrapper = smsCache.get(phoneNumber);
        if (valueWrapper != null) {
            throw new AppException(ErrorCode.MESSAGE_ALREADY_SENT);
        }

        String key = phoneNumber.concat(usage);
        String code = makeSmsCode(6);
        smsCache.put(key, code);
        String[] params = {code, String.valueOf(smsConfig.getExpireTime())};
        SmsSingleSender smsSingleSender = new SmsSingleSender(smsConfig.getAppId(), smsConfig.getAppKey());
        try {
            SmsSingleSenderResult result = smsSingleSender.sendWithParam(smsConfig.getRegion(), phoneNumber, smsConfig.getTemplateId(), params, smsConfig.getSignName(), "", "");
            if (result.result!=0) {
                smsCache.evict(key);
                throw new AppException(ErrorCode.MESSAGE_FAILED_TO_SEND);
            }
        } catch (Exception e) {
            smsCache.evict(key);
            throw new AppException(ErrorCode.MESSAGE_FAILED_TO_SEND);
        }
    }

    public static String makeSmsCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int next = random.nextInt(10);
            code.append(next);
        }
        return code.toString();
    }

    public void verify(String phoneNumber, String code, String usage) {
        String key = phoneNumber.concat(usage);
        Cache.ValueWrapper valueWrapper = smsCache.get(key);
        if (valueWrapper == null) {
            throw new AppException(ErrorCode.MESSAGE_NOT_SENT);
        }
        String cachedCode = (String) valueWrapper.get();
        if (!code.equals(cachedCode)) {
            throw new AppException(ErrorCode.INVALID_MESSAGE_CODE);
        }
        smsCache.evict(key);
    }
}
