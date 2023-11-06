package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
