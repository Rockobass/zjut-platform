package org.whatever.aha.zjut.platform.service;

import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;
import org.whatever.aha.zjut.base.util.CaptchaUtil;

@Service
public class CaptchaService {
    @Autowired
    CacheManager caffeineCacheManager;

    public String getBase64(String fingerPrint){
        CaptchaUtil captchaUtil = new CaptchaUtil(200, 80, 6, 0, "#365700");
        captchaUtil.setCharType(Captcha.TYPE_DEFAULT);
        String verifyCode = captchaUtil.text().toLowerCase();
        String base64Img = captchaUtil.toBase64();

        Cache captcha = caffeineCacheManager.getCache("Captcha");
        captcha.put(fingerPrint, verifyCode);
        return base64Img;
    }

    public void verify(String fingerPrint, String code) {
        Cache cache = caffeineCacheManager.getCache("Captcha");
        Cache.ValueWrapper value = cache.get(fingerPrint);
        if (value == null)
            throw new AppException(ErrorCode.EXPIRED_VERIFYING_CODE);
        String cachedCode = (String) value.get();
        if (!code.toLowerCase().equals(cachedCode)) {
            cache.evict(fingerPrint);
            throw new AppException(ErrorCode.UNMATCHED_VERIFYING_CODE);
        }
        cache.evict(fingerPrint);
    }
}
