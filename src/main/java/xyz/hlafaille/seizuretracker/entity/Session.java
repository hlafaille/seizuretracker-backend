package xyz.hlafaille.seizuretracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
public class Session {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID user;

    @Column(nullable = false)
    private ZonedDateTime expire;
}
