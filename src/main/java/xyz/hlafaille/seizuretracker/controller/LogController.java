package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.hlafaille.seizuretracker.service.UserServiceImpl;

@Controller
public class LogController {
    private final UserServiceImpl userService;

    @Autowired
    public LogController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/log")
    public String log(HttpServletRequest request, Model model){
        // get the user by their session id
        model.addAttribute(
                "userFirstName",
                userService.getUserBySessionCookie(request.getCookies()).getFirstName()
        );
        return "pages/log";
    }
}
