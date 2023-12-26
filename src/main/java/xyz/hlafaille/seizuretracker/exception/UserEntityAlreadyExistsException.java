package xyz.hlafaille.seizuretracker.exception;

public class UserEntityAlreadyExistsException extends Exception{
    public UserEntityAlreadyExistsException() {
        super("User entity already exists");
    }
}
