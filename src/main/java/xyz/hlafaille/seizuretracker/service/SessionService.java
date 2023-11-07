package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.*;

import java.util.UUID;

/**
 * Interface for services relating to Sessions and Session cookies.
 */
@Service
public interface SessionService {
    /**
     * Shim for Session Repository findById(). Get a Session by its ID.
     *
     * @param sessionId Session ID
     * @return Session entity
     */
    Session getSessionEntityById(UUID sessionId) throws SessionEntityMissingException;

    /**
     * Designed for use with an HttpServletRequest, this method will return our session cookie if it exists.
     *
     * @param cookies Array of Cookie(s) from HttpServletRequest
     * @return Cookie object
     */
    Cookie getSessionCookieFromBrowserCookies(Cookie[] cookies) throws SessionCookieMissingException;

    /**
     * Get a Session entity from the database by a provided cookie.
     *
     * @param cookie Session Cookie object
     * @return Session entity
     */
    Session getSessionEntityFromCookie(Cookie cookie) throws SessionEntityMissingException;

    /**
     * If a session is expired, return true. Else, return false.
     *
     * @param session Session entity
     * @return If session is expired, return true. Else, return false.
     */
    boolean isSessionExpired(Session session);

    /**
     * Checks if a session is expired.
     *
     * @param session Session entity
     */
    void checkSessionExpired(Session session) throws SessionExpiredException;

    /**
     * Get a user's session
     *
     * @param userId User ID
     * @return Session entity
     */
    Session getSessionEntityFromUserId(UUID userId) throws SessionEntityMissingException;

    /**
     * Get a user's entity from a session
     *
     * @param sessionId Session ID
     * @return User entity
     */
    User getUserEntityFromSessionId(UUID sessionId) throws SessionEntityMissingException, SessionUserMissingException;

    /**
     * End a session by its ID
     *
     * @param sessionId Session ID
     */
    void endSessionById(UUID sessionId);

    /**
     * End a session by a user's ID
     *
     * @param sessionId Session ID
     */
    void endSessionByUserId(UUID sessionId) throws SessionEntityMissingException;


    /**
     * Private method for determining if the user has a resumable session. For example, this is useful for if the
     * user goes to /login or /signup with a session cookie in their browser.
     *
     * @param cookies Array of browser cookies
     * @return True if the user has a valid session that can be resumed, false if not.
     */
    boolean isSessionResumableByBrowserCookies(Cookie[] cookies);

    UUID beginSession(String emailAddress, String password) throws UserEntityMissingException;
}
