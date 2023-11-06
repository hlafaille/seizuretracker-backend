package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hlafaille.seizuretracker.entity.Session;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
