package xyz.hlafaille.seizuretracker.exception;

public class UserPasswordMismatchException extends Exception{
    public UserPasswordMismatchException() {
        super("Provided password and user entity password do match");
    }
}
