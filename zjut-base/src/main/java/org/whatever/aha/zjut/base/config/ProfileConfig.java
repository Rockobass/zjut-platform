package org.whatever.aha.zjut.base.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author Baby_mo
 */
@Configuration
@RequiredArgsConstructor
public class ProfileConfig {

    private final ApplicationContext context;

    public String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

    public boolean isDev() {
        String activeProfile = getActiveProfile();
        return activeProfile.equals("dev") || activeProfile.equals("debug");
    }
}