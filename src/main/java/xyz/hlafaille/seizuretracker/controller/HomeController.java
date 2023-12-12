package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionUserMissingException;
import xyz.hlafaille.seizuretracker.service.SessionService;
import xyz.hlafaille.seizuretracker.service.UserService;
import xyz.hlafaille.seizuretracker.service.UserServiceImpl;

@Controller
public class HomeController {

    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public HomeController(UserServiceImpl userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/home")
    public String index(HttpServletRequest request, Model model) throws SessionCookieMissingException, SessionEntityMissingException, SessionUserMissingException {
        // get the user by their session id
        Cookie sessionCookie = sessionService.getSessionCookieFromBrowserCookies(request.getCookies());
        Session session = sessionService.getSessionEntityFromCookie(sessionCookie);
        User user = sessionService.getUserEntityFromSessionId(session.getId());
        model.addAttribute("userFirstName", user.getFirstName());
        return "views/home";
    }
}
