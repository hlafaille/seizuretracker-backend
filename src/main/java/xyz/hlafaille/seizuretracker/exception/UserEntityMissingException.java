package xyz.hlafaille.seizuretracker.exception;

public class UserEntityMissingException extends Exception {

    public UserEntityMissingException() {
        super("User entity is missing");
    }
}
