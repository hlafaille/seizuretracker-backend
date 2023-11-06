package xyz.hlafaille.seizuretracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hlafaille.seizuretracker.model.form.auth.LoginFormModel;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;
import xyz.hlafaille.seizuretracker.service.AuthService;
import xyz.hlafaille.seizuretracker.service.AuthServiceImpl;

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
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginFormModel formData) {
        UUID sessionId = authService.beginSession(
                formData.getEmailAddress(),
                formData.getPassword()
        );
        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
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
