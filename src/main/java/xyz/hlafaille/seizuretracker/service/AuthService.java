package xyz.hlafaille.seizuretracker.service;

import xyz.hlafaille.seizuretracker.entity.User;

import java.util.UUID;

/**
 * Example implementation of the AuthService
 *
 * @version 1.0.0
 */
public interface AuthService {
    User getUserById(UUID id);

    UUID createUser(String firstName, String lastName, String email, String password);

    /**
     * Encrypt the given password using argon2
     *
     * @param password Unencrypted user password
     * @return Encrypted user password
     */
    String encryptPassword(String password);

    /**
     * Check the password using argon2
     *
     * @param password Unencrypted user password
     */
    void matchPassword(User user, String password) throws RuntimeException;

    /**
     * Start a new session by logging in
     *
     * @param emailAddress User's email address
     * @param password     Users unencrypted password
     * @return Session UUID
     */
    UUID beginSession(String emailAddress, String password);
}
