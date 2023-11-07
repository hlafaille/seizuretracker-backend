package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.*;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;
import xyz.hlafaille.seizuretracker.repository.UserRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }


    /**
     * Shim for Session Repository findById(). Get a Session by its ID.
     *
     * @param sessionId Session ID
     * @return Session entity
     */
    @Override
    public Session getSessionEntityById(UUID sessionId) throws SessionEntityMissingException {
        Optional<Session> session = sessionRepository.findById(sessionId);
        if (session.isEmpty()) {
            throw new SessionEntityMissingException();
        }
        return session.get();
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
     * @param cookie Session Cookie object
     * @return Session entity
     */
    @Override
    public Session getSessionEntityFromCookie(Cookie cookie) throws SessionEntityMissingException {
        // ensure the developer passed in the right cookie
        assert cookie.getName().equals("session");

        // extract the session id from the cookie, ensure its not empty, get it into a UUID object
        String sessionCookieRawValue = cookie.getValue();
        if (sessionCookieRawValue.isEmpty()) {
            throw new SessionCookieInvalidException();
        }
        UUID sessionCookieValue = UUID.fromString(sessionCookieRawValue);

        // get the session entity
        Optional<Session> sessionEntity = sessionRepository.findById(sessionCookieValue);
        if (sessionEntity.isEmpty()) {
            throw new SessionEntityMissingException();
        }
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
        return ZonedDateTime.now().isAfter(session.getExpire());
    }

    /**
     * Checks if a session is expired.
     *
     * @param session Session entity
     */
    @Override
    public void checkSessionExpired(Session session) throws SessionExpiredException {
        if (isSessionExpired(session)) {
            throw new SessionExpiredException();
        }
    }

    /**
     * Get a user's session entity
     *
     * @param userId User ID
     * @return Session entity
     */
    @Override
    public Session getSessionEntityFromUser(UUID userId) throws SessionEntityMissingException {
        List<Session> allSessions = sessionRepository.findAll();
        for (Session session : allSessions) {
            if (session.getUser() == userId) {
                return session;
            }
        }
        throw new SessionEntityMissingException();
    }

    /**
     * Get a user's entity from a session
     *
     * @param sessionId Session ID
     * @return User entity
     */
    @Override
    public User getUserEntityFromSession(UUID sessionId) throws SessionEntityMissingException, SessionUserMissingException {
        Session session = getSessionEntityById(sessionId);
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (session.getUser().equals(user.getId())) {
                return user;
            }
        }
        throw new SessionUserMissingException();
    }
}
