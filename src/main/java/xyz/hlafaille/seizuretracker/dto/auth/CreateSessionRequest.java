package xyz.hlafaille.seizuretracker.dto.auth;

import lombok.Data;

@Data
public class CreateSessionRequest {
    String email;
    String password;
}

