package xyz.hlafaille.seizuretracker.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.hlafaille.seizuretracker.dto.GenericErrorResponse;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.service.SessionService;
import xyz.hlafaille.seizuretracker.util.InterceptorRestResponseBuilder;

import java.io.IOException;
import java.util.UUID;

@Component
public class AuthorizationEnforcementInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationEnforcementInterceptor.class);


    public AuthorizationEnforcementInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String authBearerHeader = request.getHeader("Authorization");
        if (authBearerHeader == null || authBearerHeader.startsWith("Bearer ")) {
            logger.info("invalid auth attempt: header=%s".formatted(authBearerHeader));
            InterceptorRestResponseBuilder.populateServletResponseAsRest(
                    response,
                    new GenericErrorResponse("Unauthorized"),
                    HttpStatus.UNAUTHORIZED.value()
            );
            return false;
        }
        try {
            sessionService.isSessionExpired(sessionService.getSessionEntityById(UUID.fromString(authBearerHeader)));
        } catch (SessionEntityMissingException e) {
            return false;
        }
        return true;
    }
}
