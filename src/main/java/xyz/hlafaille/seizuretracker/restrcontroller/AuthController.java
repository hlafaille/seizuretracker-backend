package xyz.hlafaille.seizuretracker.restrcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.exception.SessionAlreadyExistsException;
import xyz.hlafaille.seizuretracker.exception.UserEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.UserPasswordMismatchException;
import xyz.hlafaille.seizuretracker.model.CreateSessionRequest;
import xyz.hlafaille.seizuretracker.model.CreateSessionResponse;
import xyz.hlafaille.seizuretracker.model.GenericErrorResponse;
import xyz.hlafaille.seizuretracker.service.SessionService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final SessionService sessionService;

    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ExceptionHandler(SessionAlreadyExistsException.class)
    public ResponseEntity<GenericErrorResponse> handleSessionAlreadyExistsException() {
        return ResponseEntity
                .status(400)
                .body(new GenericErrorResponse("Session already exists"));
    }

    /**
     * Create a new Session, returning a CreateSessionResponse containing an accessToken
     *
     * @param body CreateSessionRequest schema
     */
    @PostMapping("/session")
    public ResponseEntity<CreateSessionResponse> createSession(
            @RequestBody CreateSessionRequest body
    ) throws UserEntityMissingException, UserPasswordMismatchException, SessionAlreadyExistsException {
        UUID sessionId = sessionService.beginSession(body.getEmail(), body.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateSessionResponse(sessionId));
    }
}
