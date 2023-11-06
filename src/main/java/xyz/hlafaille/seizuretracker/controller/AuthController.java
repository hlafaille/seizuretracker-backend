package xyz.hlafaille.seizuretracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;
import xyz.hlafaille.seizuretracker.service.AuthService;
import xyz.hlafaille.seizuretracker.service.AuthServiceImpl;

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
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin() {

        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("formData", new SignupFormModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute SignupFormModel formData
    ){
        authService.createUser(
                formData.getFirstName(),
                formData.getLastName(),
                formData.getEmailAddress(),
                formData.getPassword()
        );
        return "redirect:/login?newAccount=true";
    }
}
