package xyz.hlafaille.seizuretracker.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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
    private static final int ARGON_SALT_LENGTH = 16;
    private static final int ARGON_HASH_LENGTH = 128;
    private static final int ARGON_PARALLELISM = 8;
    private static final int ARGON_MEMORY = 1024;
    private static final int ARGON_ITERATIONS = 16;

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
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(
                ARGON_SALT_LENGTH, ARGON_HASH_LENGTH, ARGON_PARALLELISM, ARGON_MEMORY, ARGON_ITERATIONS
        );
        return argon2PasswordEncoder.encode(password);
    }

    @Override
    public void matchPassword(User user, String password) {
        // get the user by their email
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(
                ARGON_SALT_LENGTH, ARGON_HASH_LENGTH, ARGON_PARALLELISM, ARGON_MEMORY, ARGON_ITERATIONS
        );

        // check if the password matches
        boolean passwordMatches = argon2PasswordEncoder.matches(password, user.getPassword());
        if (!passwordMatches) {
            throw new RuntimeException("Given password does not match the users password");
        }
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
        user.setPassword(encryptPassword(password));
        userRepository.save(user);
        return userId;
    }
}
