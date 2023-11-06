package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.hlafaille.seizuretracker.model.SignupFormModel;

@Controller
public class LoginController {
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
    public void doSignup(@ModelAttribute SignupFormModel formData, BindingResult bindingResult){
        System.out.println("GOT SIGNUP REQUEST" + formData.toString());
        System.out.println(bindingResult.getAllErrors());
    }
}
