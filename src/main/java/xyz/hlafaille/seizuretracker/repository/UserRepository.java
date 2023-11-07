package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, QueryByExampleExecutor<User> {
    Optional<User> findByEmail(String email);
}
