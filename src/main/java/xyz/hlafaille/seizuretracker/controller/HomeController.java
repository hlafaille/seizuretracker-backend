package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.hlafaille.seizuretracker.repository.SessionRepository;
import xyz.hlafaille.seizuretracker.service.AuthService;
import xyz.hlafaille.seizuretracker.service.UserServiceImpl;

@Controller
public class HomeController {

    private final UserServiceImpl userService;

    @Autowired
    public HomeController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String index(HttpServletRequest request, Model model){
        // get the user by their session id
        model.addAttribute(
                "userFirstName",
                userService.getUserBySessionCookie(request.getCookies()).getFirstName()
        );
        return "home";
    }
}
