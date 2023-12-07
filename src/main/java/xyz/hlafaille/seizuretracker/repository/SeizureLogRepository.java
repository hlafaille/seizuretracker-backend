package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeizureLogRepository extends JpaRepository<SeizureLog, UUID>, QueryByExampleExecutor<SeizureLog> {
    List<SeizureLog> findAllByUser(UUID userId);
}

