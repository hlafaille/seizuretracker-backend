package xyz.hlafaille.seizuretracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
public class User {
    @Id
    private UUID id;

    @Column(name = "first_name", nullable = false, unique = true, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = true, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 255)
    private String password;
}
