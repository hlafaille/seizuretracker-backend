package xyz.hlafaille.seizuretracker.model;

import lombok.Data;
import lombok.Getter;

@Data
public class SignupFormModel {
    private String emailAddress;
    private String password;
}
