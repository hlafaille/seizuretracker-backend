package xyz.hlafaille.seizuretracker.exception;

/**
 * Thrown when the `session` cookie could not be found
 */
public class SessionCookieMissingException extends Exception {
    public SessionCookieMissingException() {
        super("Session cookie is missing");
    }
}
