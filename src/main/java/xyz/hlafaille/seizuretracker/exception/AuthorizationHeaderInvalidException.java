package xyz.hlafaille.seizuretracker.exception;

public class AuthorizationHeaderInvalidException extends Exception{
    public AuthorizationHeaderInvalidException() {
        super("Authorization header was not provided or does not follow 'Bearer' format");
    }
}
