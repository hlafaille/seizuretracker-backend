package xyz.hlafaille.seizuretracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateUserResponse {
    private UUID userId;
}
