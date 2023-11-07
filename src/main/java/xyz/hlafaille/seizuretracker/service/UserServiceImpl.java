package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.SessionCookieInvalidException;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;
import xyz.hlafaille.seizuretracker.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get the user by a session ID
     *
     * @param sessionId Session UUID
     * @return User entity
     */
    @Deprecated
    @Override
    public User getUserBySessionId(UUID sessionId) {
        return null;
    }

    /**
     * Get the user by their session Cookie
     *
     * @param cookies Array of Cookie(s)
     * @return User entity
     */
    @Deprecated
    @Override
    public User getUserBySessionCookie(Cookie[] cookies) throws SessionCookieInvalidException {
        // if there is no cookies (cookies is null), throw SessionCookieInvalidException
        if (cookies == null) {
            throw new SessionCookieInvalidException();
        }

        // iterate over the cookies, find our "session"
        Cookie sessionCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session")) {
                sessionCookie = cookie;
            }
        }

        // if there was no session cookie (but there are others)
        if (sessionCookie == null) {
            throw new SessionCookieInvalidException();
        }

        // get the session id as uuid from the cookie, then get the session from the database
        UUID sessionIdFromCookie = UUID.fromString(sessionCookie.getValue());
        Optional<Session> session = sessionRepository.findById(sessionIdFromCookie);
        if (session.isEmpty()) {
            throw new SessionCookieInvalidException();
        }

        // get the user from the database by its session id
        Optional<User> user = userRepository.findById(session.get().getUser());
        if (user.isEmpty()) {
            throw new RuntimeException("User for this session was not found. This should not happen.");
        }

        return user.get();
    }
}
