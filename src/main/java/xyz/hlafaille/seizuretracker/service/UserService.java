package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.UUID;

/**
 * Interface for interacting with Users
 */
public interface UserService {
    User getUserById(UUID userId);

    UUID createUser(String firstName, String lastName, String email, String password);

}
