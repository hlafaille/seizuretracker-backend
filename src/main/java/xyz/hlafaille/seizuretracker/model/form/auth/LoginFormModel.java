package xyz.hlafaille.seizuretracker.model.form.auth;

import lombok.Data;

@Data
public class LoginFormModel {
    private String emailAddress;
    private String password;
}
