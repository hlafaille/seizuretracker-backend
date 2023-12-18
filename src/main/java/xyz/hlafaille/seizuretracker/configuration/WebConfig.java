package xyz.hlafaille.seizuretracker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.hlafaille.seizuretracker.component.HtmxRequestArgumentResolver;
import xyz.hlafaille.seizuretracker.interceptor.SessionEnforcementInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SessionEnforcementInterceptor sessionEnforcementInterceptor;
    private final String[] invalidSessionAllowedPaths = {"/login", "/error", "/signup"};
    private final HtmxRequestArgumentResolver htmxRequestArgumentResolver;


    // @Autowired
    public WebConfig(SessionEnforcementInterceptor sessionEnforcementInterceptor, HtmxRequestArgumentResolver htmxRequestArgumentResolver) {
        this.sessionEnforcementInterceptor = sessionEnforcementInterceptor;
        this.htmxRequestArgumentResolver = htmxRequestArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(sessionEnforcementInterceptor).excludePathPatterns(invalidSessionAllowedPaths);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(htmxRequestArgumentResolver);
    }
}
