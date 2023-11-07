package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.exception.SessionCookieInvalidException;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionExpiredException;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Designed for use with an HttpServletRequest, this method will return our session cookie if it exists.
     *
     * @param cookies Array of Cookie(s) from HttpServletRequest
     * @return Cookie object
     */
    @Override
    public Cookie getCookieFromBrowserCookies(Cookie[] cookies) throws SessionCookieMissingException {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                return cookie;
            }
        }
        throw new SessionCookieMissingException();
    }

    /**
     * Get a Session entity from the database by a provided cookie.
     *
     * @return Session entity
     */
    @Override
    public Session getSessionEntityFromCookie(Cookie cookie) throws SessionEntityMissingException {
        // ensure the developer passed in the right cookie
        assert cookie.getName().equals("session");

        // extract the session id from the cookie, ensure its not empty, get it into a UUID object
        String sessionCookieRawValue = cookie.getValue();
        if (sessionCookieRawValue.isEmpty()) { throw new SessionCookieInvalidException(); }
        UUID sessionCookieValue = UUID.fromString(sessionCookieRawValue);

        // get the session entity
        Optional<Session> sessionEntity = sessionRepository.findById(sessionCookieValue);
        if (sessionEntity.isEmpty()) { throw new SessionEntityMissingException();}
        return sessionEntity.get();
    }

    /**
     * If a session is expired, return true. Else, return false.
     *
     * @param session Session entity
     * @return If session is expired, return true. Else, return false.
     */
    @Override
    public boolean isSessionExpired(Session session) {
        return false;
    }

    /**
     * Checks if a session is expired.
     *
     * @param session Session entity
     */
    @Override
    public void checkSessionExpired(Session session) throws SessionExpiredException {

    }

    /**
     * Get a user's session
     *
     * @param userId User ID
     * @return Session entity
     */
    @Override
    public Session getSessionEntityFromUser(UUID userId) {
        return null;
    }
}
