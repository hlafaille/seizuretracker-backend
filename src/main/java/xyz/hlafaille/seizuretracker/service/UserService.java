package xyz.hlafaille.seizuretracker.service;

import jakarta.servlet.http.Cookie;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.UserEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.UserPasswordMismatchException;

import java.util.List;
import java.util.UUID;

/**
 * Interface for interacting with Users
 */
public interface UserService {
    User getUserEntityById(UUID userId) throws UserEntityMissingException;

    User getUserEntityByEmail(String email) throws UserEntityMissingException;

    List<User> getAllUsers();

    UUID createUser(String firstName, String lastName, String email, String password);

    String encryptPassword(String password);

    boolean isPasswordMatching(User user, String password);

    void matchPassword(User user, String password) throws UserPasswordMismatchException;
}
