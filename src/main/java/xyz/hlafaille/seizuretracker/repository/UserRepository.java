package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    default User findUserByEmail(String email) throws RuntimeException {
        List<User> users = this.findAll();
        System.out.println("Email " + email);
        for (User x : users) {
            if (x.getEmail().equals(email)) {
                System.out.println(x);
                return x;
            }
        }
        throw new RuntimeException("User not found");
    }
}