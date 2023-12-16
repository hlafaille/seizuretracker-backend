package xyz.hlafaille.seizuretracker.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;

@Repository
public interface SeizureLogRepository
    extends
        JpaRepository<SeizureLog, UUID>, QueryByExampleExecutor<SeizureLog> {
    List<SeizureLog> findAllByUser(UUID userId);
}
