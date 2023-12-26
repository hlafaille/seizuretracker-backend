package xyz.hlafaille.seizuretracker.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MoodSurvey {
    @Id
    private UUID id;

}
