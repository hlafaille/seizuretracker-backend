package xyz.hlafaille.seizuretracker.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.MoodSurveyAllowedMoods;

@Entity
@Getter
@Setter
public class MoodSurvey {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private User user;

    @Column
    private MoodSurveyAllowedMoods mood;

    @Column
    private Instant instant = Instant.now();
}
