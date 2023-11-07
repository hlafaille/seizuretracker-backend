package xyz.hlafaille.seizuretracker.exception;

/**
 * Thrown when the requested Session entity is missing in the database
 */
public class SessionEntityMissingException extends Exception {
    public SessionEntityMissingException() {
        super("Session entity was not found");
    }
}
