package xyz.hlafaille.seizuretracker.restrcontroller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hlafaille.seizuretracker.model.CreateUserRequest;
import xyz.hlafaille.seizuretracker.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void createUser(@RequestBody CreateUserRequest body, HttpServletResponse response) {
        userService.createUser(
                body.getFirstName(),
                body.getLastName(),
                body.getEmailAddress(),
                body.getPassword()
        );
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
