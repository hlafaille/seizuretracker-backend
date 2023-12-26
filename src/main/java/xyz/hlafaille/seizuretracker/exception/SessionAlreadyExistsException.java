package xyz.hlafaille.seizuretracker.exception;

public class SessionAlreadyExistsException extends Exception {
    public SessionAlreadyExistsException() {
        super("Session already exists");
    }
}
