package xyz.hlafaille.seizuretracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import xyz.hlafaille.seizuretracker.entity.MoodSurvey;

import java.util.UUID;

public interface MoodSurveyRepository extends JpaRepository<MoodSurvey, UUID>, QueryByExampleExecutor<MoodSurvey> {
}
