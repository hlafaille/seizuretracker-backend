package xyz.hlafaille.seizuretracker.service;

import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.UUID;

/**
 * Implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public User getUserById(UUID userId) {
        return null;
    }

    @Override
    public UUID createUser(String firstName, String lastName, String email, String password) {
        return null;
    }
}
