package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.SessionCookieInvalidException;
import xyz.hlafaille.seizuretracker.model.form.auth.LoginFormModel;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;
import xyz.hlafaille.seizuretracker.service.AuthService;
import xyz.hlafaille.seizuretracker.service.UserService;

import java.util.UUID;

@Controller
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(
            HttpServletRequest request,
            @RequestParam(required = false) boolean newAccount,
            Model model
    ) {
        // try to get the user by a session cookie, if they have a valid session cookie, redirect them to /home
        try {
            User sessionUser = userService.getUserBySessionCookie(request.getCookies());
        } catch (SessionCookieInvalidException e) {
            model.addAttribute("newAccount", newAccount);
            return "pages/login";
        }
        return "redirect:/home";
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
    public String signup(HttpServletRequest request) {
        // try to get the user by a session cookie, if they have a valid session cookie, redirect them to /home
        try {
            User sessionUser = userService.getUserBySessionCookie(request.getCookies());
        } catch (SessionCookieInvalidException e) {
            return "pages/signup";
        }
        return "redirect:/home";
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

    /**
     * Log out the current user by getting their session cookie, invalidating it, deleting it from the browser cookies,
     * and redirecting the user to /login
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        try {
            User sessionUser = userService.getUserBySessionCookie(request.getCookies());
        } catch (SessionCookieInvalidException e) {
            return "redirect:/login";
        }
        return "redirect:/login";
    }
}
