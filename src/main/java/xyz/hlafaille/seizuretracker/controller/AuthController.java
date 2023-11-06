package xyz.hlafaille.seizuretracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;

@Controller
public class AuthController {
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("formData", new SignupFormModel());
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@ModelAttribute SignupFormModel formData){
        System.out.println("GOT SIGNUP REQUEST" + formData.toString());
        return "redirect:/login?newAccount=true";
    }
}
