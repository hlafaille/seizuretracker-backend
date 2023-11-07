package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.model.form.auth.LoginFormModel;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;
import xyz.hlafaille.seizuretracker.service.AuthService;

import java.util.UUID;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(required = false) boolean newAccount,
            Model model
    ) {
        model.addAttribute("newAccount", newAccount);
        return "pages/login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginFormModel formData, HttpServletResponse response) {
        // start the session
        UUID sessionId = authService.beginSession(
                formData.getEmailAddress(),
                formData.getPassword()
        );

        // set the cookie
        Cookie sessionCookie = new Cookie("session", sessionId.toString());
        response.addCookie(sessionCookie);

        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute SignupFormModel formData
    ) {
        authService.createUser(
                formData.getFirstName(),
                formData.getLastName(),
                formData.getEmailAddress(),
                formData.getPassword()
        );
        return "redirect:/login?newAccount=true";
    }
}
