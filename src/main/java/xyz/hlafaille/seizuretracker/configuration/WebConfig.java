package xyz.hlafaille.seizuretracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.hlafaille.seizuretracker.interceptor.AuthorizationEnforcementInterceptor;
import xyz.hlafaille.seizuretracker.interceptor.CorsInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CorsInterceptor corsInterceptor;
    private final AuthorizationEnforcementInterceptor authorizationEnforcementInterceptor;

    public WebConfig(CorsInterceptor corsInterceptor, AuthorizationEnforcementInterceptor authorizationEnforcementInterceptor) {
        this.corsInterceptor = corsInterceptor;
        this.authorizationEnforcementInterceptor = authorizationEnforcementInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(authorizationEnforcementInterceptor)
                .addPathPatterns(
                        "/v1/auth/**",
                        "/v1/users/**"
                )
                .excludePathPatterns(
                        "/v1/users/createAccount",
                        "/v1/auth/session"
                );
    }
}