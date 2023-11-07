package xyz.hlafaille.seizuretracker.exception;

/**
 * Exception thrown when a `session` cookie was found, but it is not considered valid. This can happen for multiple reasons.
 * For example, if the `session` cookie is not a UUID or is null.
 */
public class SessionCookieInvalidException extends RuntimeException {
    public SessionCookieInvalidException() {
        super("Session cookie is invalid");
    }
}
