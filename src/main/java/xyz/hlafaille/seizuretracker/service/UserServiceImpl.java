package xyz.hlafaille.seizuretracker.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.UserEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.UserPasswordMismatchException;
import xyz.hlafaille.seizuretracker.repository.UserRepository;

/**
 * Implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {

    private static final int ARGON_SALT_LENGTH = 16;
    private static final int ARGON_HASH_LENGTH = 128;
    private static final int ARGON_PARALLELISM = 8;
    private static final int ARGON_MEMORY = 1024;
    private static final int ARGON_ITERATIONS = 16;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserEntityById(UUID userId) throws UserEntityMissingException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserEntityMissingException();
        }
        return user.get();
    }

    @Override
    public User getUserEntityByEmail(String email) throws UserEntityMissingException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserEntityMissingException();
        }
        return user.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

    @Override
    public String encryptPassword(String password) {
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(
            ARGON_SALT_LENGTH,
            ARGON_HASH_LENGTH,
            ARGON_PARALLELISM,
            ARGON_MEMORY,
            ARGON_ITERATIONS
        );
        return argon2PasswordEncoder.encode(password);
    }

    @Override
    public boolean isPasswordMatching(User user, String password) {
        // get the user by their email
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(
            ARGON_SALT_LENGTH,
            ARGON_HASH_LENGTH,
            ARGON_PARALLELISM,
            ARGON_MEMORY,
            ARGON_ITERATIONS
        );

        // check if the password matches
        return argon2PasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void matchPassword(User user, String password) throws UserPasswordMismatchException {
        if (!isPasswordMatching(user, password)) {
            throw new UserPasswordMismatchException();
        }
    }
}
