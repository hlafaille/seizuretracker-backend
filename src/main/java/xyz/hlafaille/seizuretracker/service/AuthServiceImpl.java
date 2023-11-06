package xyz.hlafaille.seizuretracker.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public UUID beginSession(String emailAddress, String password) throws RuntimeException {
        User user = userRepository.findUserByEmail(emailAddress);
        String encryptedPassword = encryptPassword(password);
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new RuntimeException("Invalid password");
        }
        return user.getId();
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
