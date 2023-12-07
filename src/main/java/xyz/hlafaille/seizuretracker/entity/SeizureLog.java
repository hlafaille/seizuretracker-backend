package xyz.hlafaille.seizuretracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class SeizureLog {
    @Id
    private UUID id;

    @Column(nullable = false)
    private Integer severity;

    @Column(nullable = false)
    private UUID user;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false, name = "before_seizure_note")
    private String beforeSeizureNote;

    @Column(nullable = false, name = "during_seizure_note")
    private String duringSeizureNote;

    @Column(nullable = false, name = "after_seizure_note")
    private String afterSeizureNote;

    @Column(nullable = false, name = "hospital_visit_occurred")
    private boolean hospitalVisitOccurred;

    @Column(nullable = false, name = "additional_comment")
    private String additionalComment;

    @Column(nullable = false)
    private Instant instant = Instant.now();
}
