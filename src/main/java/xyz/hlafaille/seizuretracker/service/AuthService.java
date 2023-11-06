package xyz.hlafaille.seizuretracker.service;

import xyz.hlafaille.seizuretracker.entity.User;
import java.util.UUID;

/**
 * Example implementation of the AuthService
 * @version 1.0.0
 */
public interface AuthService {
    User getUserById(UUID id);
    UUID createUser(String firstName, String lastName, String email, String password);
}
