package xyz.hlafaille.seizuretracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class SeizureLog {
    @Id
    private UUID id;

    @Column
    private Integer severity;

    @Column
    private UUID user;

    @Column
    private Integer duration;

    @Column(name = "before_seizure_note")
    private String beforeSeizureNote;

    @Column(name = "during_seizure_note")
    private String duringSeizureNote;

    @Column(name = "after_seizure_note")
    private String afterSeizureNote;

    @Column(name = "hospital_visit_occurred")
    private boolean hospitalVisitOccurred;

    @Column(name = "additional_comment")
    private String additionalComment;

    @Column
    private Instant instant = Instant.now();

    @Column(nullable = false, name = "is_draft")
    private boolean isDraft;
}
