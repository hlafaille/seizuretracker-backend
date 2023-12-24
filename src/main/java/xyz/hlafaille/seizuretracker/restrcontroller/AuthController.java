package xyz.hlafaille.seizuretracker.restrcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hlafaille.seizuretracker.exception.UserEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.UserPasswordMismatchException;
import xyz.hlafaille.seizuretracker.model.CreateSessionRequest;
import xyz.hlafaille.seizuretracker.model.CreateSessionResponse;
import xyz.hlafaille.seizuretracker.service.SessionService;
import java.util.UUID;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final SessionService sessionService;

    public AuthController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Create a new Session, returning a CreateSessionResponse containing an accessToken
     * @param body CreateSessionRequest schema
     */
    @PostMapping("/session")
    public CreateSessionResponse createSession(
            @RequestBody CreateSessionRequest body
    ) throws UserEntityMissingException, UserPasswordMismatchException {
        UUID sessionId = sessionService.beginSession(body.getEmail(), body.getPassword());
        return new CreateSessionResponse(sessionId);
    }
}
