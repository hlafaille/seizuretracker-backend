package xyz.hlafaille.seizuretracker.model;

import lombok.Data;

@Data
public class CreateSessionRequest {
    String email;
    String password;
}

