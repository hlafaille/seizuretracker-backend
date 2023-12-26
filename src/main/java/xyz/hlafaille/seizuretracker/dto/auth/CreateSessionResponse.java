package xyz.hlafaille.seizuretracker.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateSessionResponse {
    UUID accessToken;
}
