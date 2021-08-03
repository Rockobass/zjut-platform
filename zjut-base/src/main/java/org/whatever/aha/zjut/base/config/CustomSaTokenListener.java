package org.whatever.aha.zjut.base.config;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author GuiYi Yang
 */
@Component
@Slf4j
public class CustomSaTokenListener implements SaTokenListener {

    @Override
    public void doLogin(String loginType, Object loginId, SaLoginModel loginModel) {
        Date date = new Date();
        log.info(date.toString() + "  用户   " + loginId + "登录  " + loginModel.toString());
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doLogoutByLoginId(String loginType, Object loginId, String tokenValue, String device) {

    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue, String device) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }
}
