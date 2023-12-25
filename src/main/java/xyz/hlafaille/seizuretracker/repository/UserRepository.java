package xyz.hlafaille.seizuretracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.hlafaille.seizuretracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, QueryByExampleExecutor<User> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
