package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;

import java.util.UUID;

public interface SeizureLogRepository extends JpaRepository<SeizureLog, UUID>, QueryByExampleExecutor<SeizureLog> {
}

