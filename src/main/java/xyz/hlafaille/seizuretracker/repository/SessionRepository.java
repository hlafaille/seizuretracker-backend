package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    default Session findSessionByUser(UUID userId) throws RuntimeException {
        List<Session> sessions = this.findAll();
        for (Session x : sessions) {
            if (x.getUser().equals(userId)) {
                return x;
            }
        }
        throw new RuntimeException("Session with User ID not found");
    }
}
