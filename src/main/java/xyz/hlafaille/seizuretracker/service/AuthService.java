package xyz.hlafaille.seizuretracker.service;

import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.repository.UserRepository;

import java.util.UUID;

/**
 * Example implementation of the AuthService
 * @version 1.0.0
 */
@Service
public interface AuthService {
    User getUserById(UUID id);
    UUID createUser(String firstName, String lastName, String email, String password);
}
