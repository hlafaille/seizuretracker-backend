package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.*;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, UserService userService) {
        this.sessionRepository = sessionRepository;
        this.userService = userService;
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
    public Cookie getSessionCookieFromBrowserCookies(Cookie[] cookies) throws SessionCookieMissingException {
        if (cookies == null || cookies.length == 0) {
            throw new SessionCookieMissingException();
        }
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
    public Session getSessionEntityFromUserId(UUID userId) throws SessionEntityMissingException {
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
    public User getUserEntityFromSessionId(UUID sessionId)
        throws SessionEntityMissingException, SessionUserMissingException {
        Session session = getSessionEntityById(sessionId);
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            if (session.getUser().equals(user.getId())) {
                return user;
            }
        }
        throw new SessionUserMissingException();
    }

    /**
     * End a session by a user's ID
     *
     * @param sessionId Session ID
     */
    @Override
    public void endSessionById(UUID sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    /**
     * End a session by a user's ID
     *
     * @param userId User ID
     */
    @Override
    public void endSessionByUserId(UUID userId) throws SessionEntityMissingException {
        Session session = getSessionEntityFromUserId(userId);
        sessionRepository.deleteById(session.getId());
    }

    /**
     * Private method for determining if the user has a resumable session. For example, this is useful for if the
     * user goes to /login or /signup with a session cookie in their browser.
     *
     * @param cookies Array of browser cookies
     * @return True if the user has a valid session that can be resumed, false if not.
     */
    @Override
    public boolean isSessionResumableByBrowserCookies(Cookie[] cookies) {
        Session session = null;
        try {
            // get session cookie, session and user from session. then check if session is expired.
            Cookie sessionCookie = getSessionCookieFromBrowserCookies(cookies);
            session = getSessionEntityFromCookie(sessionCookie);
            User sessionUser = getUserEntityFromSessionId(session.getId());
            checkSessionExpired(session);
        } catch (
            SessionCookieInvalidException
            | SessionEntityMissingException
            | SessionCookieMissingException
            | SessionUserMissingException e
        ) {
            return false;
        } catch (SessionExpiredException e) {
            // note, `session` should be assigned if we're getting this exception :)
            endSessionById(session.getId());
            return false;
        }
        return true;
    }

    public UUID beginSession(String emailAddress, String password)
            throws UserEntityMissingException, UserPasswordMismatchException, SessionAlreadyExistsException {
        // find the user from the database, ensure that their password matches
        User user = userService.getUserEntityByEmail(emailAddress);
        userService.matchPassword(user, password);

        // determine when this session should expire
        ZonedDateTime expiresAt = ZonedDateTime.now(ZoneId.of("UTC")).plusHours(3);

        // create a new session
        UUID sessionId = UUID.randomUUID();
        Session session = new Session();
        session.setId(sessionId);
        session.setUser(user.getId());
        session.setExpire(expiresAt);

        // return existing session id if it already exists
        try {
            sessionRepository.save(session);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().toLowerCase().contains("duplicate entry")) {
                throw new SessionAlreadyExistsException();
            }
        }
        logger.info("user:%s began session".formatted(user.getId().toString()));
        return sessionId;
    }
}
