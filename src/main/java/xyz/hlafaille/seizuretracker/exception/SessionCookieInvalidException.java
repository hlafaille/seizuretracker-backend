package xyz.hlafaille.seizuretracker.exception;

/**
 * Exception thrown when a session cookie is invalid (ex: not a UUID, non existent or null).
 */
public class SessionCookieInvalidException extends RuntimeException{
    public SessionCookieInvalidException() {
        super("Session cookie is invalid");
    }
}
