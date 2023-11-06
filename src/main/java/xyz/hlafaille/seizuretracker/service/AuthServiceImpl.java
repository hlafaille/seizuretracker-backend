package xyz.hlafaille.seizuretracker.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.repository.UserRepository;

import java.util.UUID;

/**
 * AuthService implementation
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public UUID createUser(String firstName, String lastName, String email, String password) {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return userId;
    }
}
