package xyz.hlafaille.seizuretracker.exception;

/**
 * Represents a scenario in which the user for a session cannot be found. In theory, this should never happen as the
 * database session user ID column has a foreign key dependency on the users table.
 */
public class SessionUserMissingException extends Exception {

    public SessionUserMissingException() {
        super("User not found");
    }
}
