package xyz.hlafaille.seizuretracker.exception;

public class SessionExpiredException extends Exception {

    public SessionExpiredException() {
        super("Session expired");
    }
}
