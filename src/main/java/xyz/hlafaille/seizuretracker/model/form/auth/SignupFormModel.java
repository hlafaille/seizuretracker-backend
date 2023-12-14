package xyz.hlafaille.seizuretracker.model.form.auth;

import lombok.Data;

@Data
public class SignupFormModel {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
}
