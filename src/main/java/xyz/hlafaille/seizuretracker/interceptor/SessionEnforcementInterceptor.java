package xyz.hlafaille.seizuretracker.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;
import xyz.hlafaille.seizuretracker.service.SeizureLogService;
import xyz.hlafaille.seizuretracker.service.SessionService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Intercept every request, ensuring that they have a valid session
 */
@Component
public class SessionEnforcementInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;
    private final Logger logger = LoggerFactory.getLogger(SessionEnforcementInterceptor.class);

    @Autowired
    public SessionEnforcementInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws SessionCookieMissingException, SessionEntityMissingException, IOException {
        Cookie cookie = sessionService.getSessionCookieFromBrowserCookies(request.getCookies());
        Session session = sessionService.getSessionEntityFromCookie(cookie);
        if (sessionService.isSessionExpired(session)) {
            logger.info("session:%s is expired: redirecting user".formatted(session.getId().toString()));
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
