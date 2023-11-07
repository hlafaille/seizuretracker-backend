package xyz.hlafaille.seizuretracker.service;

import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.UserEntityMissingException;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserEntityById(UUID userId) throws UserEntityMissingException {
        return null;
    }

    @Override
    public User getUserEntityByEmail(String email) throws UserEntityMissingException {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public UUID createUser(String firstName, String lastName, String email, String password) {
        return null;
    }

    @Override
    public String encryptPassword(String password) {
        return null;
    }

    @Override
    public boolean isPasswordMatching(User user, String password) {
        return false;
    }

    @Override
    public void matchPassword(User user, String password) {

    }
}
