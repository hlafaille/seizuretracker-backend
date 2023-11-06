package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.UUID;

/**
 * Example implementation of the UserService
 *
 * @version 1.0.0
 */
public interface UserService {
    /**
     * Get the user by a session ID
     * @param sessionId Session UUID
     * @return User entity
     */
    User getUserBySessionId(UUID sessionId);

    /**
     * Get the user by their session Cookie
     * @param cookies Array of Cookie(s)
     * @return User entity
     */
    User getUserBySessionCookie(Cookie[] cookies);
}
