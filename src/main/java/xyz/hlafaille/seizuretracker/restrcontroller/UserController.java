package xyz.hlafaille.seizuretracker.restrcontroller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.exception.UserEntityAlreadyExistsException;
import xyz.hlafaille.seizuretracker.dto.user.CreateNewAccountRequest;
import xyz.hlafaille.seizuretracker.dto.user.CreateNewAccountResponse;
import xyz.hlafaille.seizuretracker.dto.GenericErrorResponse;
import xyz.hlafaille.seizuretracker.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(UserEntityAlreadyExistsException.class)
    public ResponseEntity<GenericErrorResponse> handleUserEntityAlreadyExistsException() {
        return ResponseEntity
                .status(400)
                .body(new GenericErrorResponse("A user with that Email already exists"));
    }

    @PostMapping("/createAccount")
    public ResponseEntity<CreateNewAccountResponse> createUser(@RequestBody CreateNewAccountRequest body, HttpServletResponse response) throws UserEntityAlreadyExistsException {
        UUID userId = userService.createUser(
                body.getFirstName(),
                body.getLastName(),
                body.getEmailAddress(),
                body.getPassword()
        );
        return ResponseEntity
                .status(201)
                .body(new CreateNewAccountResponse(userId));
    }

}
