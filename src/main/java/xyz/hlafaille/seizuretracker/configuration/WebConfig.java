package xyz.hlafaille.seizuretracker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.hlafaille.seizuretracker.interceptor.SessionEnforcementInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SessionEnforcementInterceptor sessionEnforcementInterceptor;
    private final String[] invalidSessionAllowedPaths = {
        "/login",
        "/error",
        "/signup",
    };

    // @Autowired
    public WebConfig(
        SessionEnforcementInterceptor sessionEnforcementInterceptor
    ) {
        this.sessionEnforcementInterceptor = sessionEnforcementInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(sessionEnforcementInterceptor).excludePathPatterns(invalidSessionAllowedPaths);
    }
}
