package xyz.hlafaille.seizuretracker.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Intercept every request, ensuring that they have a valid session
 */
@Component
public class SessionEnforcementInterceptor implements HandlerInterceptor {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionEnforcementInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Cookie[] cookies = request.getCookies();
        Cookie sessionCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                sessionCookie = cookie;
                break;
            }
            response.sendRedirect("/login");
            return false;
        }

        // if the cookie is not set, redirect to /login
        if (sessionCookie == null) {
            response.sendRedirect("/login");
            return false;
        }

        // get the session by ID, ensure that it is actually returned
        Optional<Session> session = sessionRepository.findById(UUID.fromString(sessionCookie.getValue()));
        if (session.isEmpty()) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
