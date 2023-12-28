package xyz.hlafaille.seizuretracker.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.hlafaille.seizuretracker.argumentresolver.AuthenticatedUserArgumentResolver;
import xyz.hlafaille.seizuretracker.interceptor.AuthorizationEnforcementInterceptor;
import xyz.hlafaille.seizuretracker.interceptor.CorsInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CorsInterceptor corsInterceptor;
    private final AuthorizationEnforcementInterceptor authorizationEnforcementInterceptor;
    private final AuthenticatedUserArgumentResolver authenticatedUserArgumentResolver;

    public WebConfig(CorsInterceptor corsInterceptor, AuthorizationEnforcementInterceptor authorizationEnforcementInterceptor, AuthenticatedUserArgumentResolver authenticatedUserArgumentResolver) {
        this.corsInterceptor = corsInterceptor;
        this.authorizationEnforcementInterceptor = authorizationEnforcementInterceptor;
        this.authenticatedUserArgumentResolver = authenticatedUserArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor);
        registry.addInterceptor(authorizationEnforcementInterceptor)
                .addPathPatterns(
                        "/auth/**",
                        "/user/**"
                )
                .excludePathPatterns(
                        "/user/createAccount",
                        "/auth/session"
                );
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticatedUserArgumentResolver);
    }
}