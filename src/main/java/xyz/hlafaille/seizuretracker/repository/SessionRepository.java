package xyz.hlafaille.seizuretracker.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.hlafaille.seizuretracker.entity.Session;

@Repository
public interface SessionRepository
    extends JpaRepository<Session, UUID>, QueryByExampleExecutor<Session> {
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
