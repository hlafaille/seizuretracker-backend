package xyz.hlafaille.seizuretracker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
}
