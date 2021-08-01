package org.whatever.aha.zjut.platform.service;

import com.wf.captcha.base.Captcha;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;
import org.whatever.aha.zjut.base.util.CaptchaUtil;

@Service
public class CaptchaService {

    final CacheManager caffeineCacheManager;
    final Cache captchaCache;

    public CaptchaService(CacheManager caffeineCacheManager) {
        this.caffeineCacheManager = caffeineCacheManager;
        this.captchaCache = caffeineCacheManager.getCache("Captcha");
    }

    /**
     * 生成base64验证码
     */
    public String getBase64(String fingerPrint) {
        CaptchaUtil captchaUtil = new CaptchaUtil(200, 80, 6, 0, "#365700");
        captchaUtil.setCharType(Captcha.TYPE_DEFAULT);
        String verifyCode = captchaUtil.text().toLowerCase();
        String base64Img = captchaUtil.toBase64();

        captchaCache.put(fingerPrint, verifyCode);
        return base64Img;
    }

    /**
     * 校对验证码
     */
    public void verify(String fingerPrint, String code) {
        Cache.ValueWrapper value = captchaCache.get(fingerPrint);
        if (value == null)
            throw new AppException(ErrorCode.EXPIRED_VERIFYING_CODE);
        String cachedCode = (String) value.get();
        if (!code.toLowerCase().equals(cachedCode)) {
            captchaCache.evict(fingerPrint);
            throw new AppException(ErrorCode.UNMATCHED_VERIFYING_CODE);
        }
        captchaCache.evict(fingerPrint);
    }
}
