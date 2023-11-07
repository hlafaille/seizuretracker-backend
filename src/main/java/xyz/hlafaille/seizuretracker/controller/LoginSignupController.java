package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.SessionCookieInvalidException;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionUserMissingException;
import xyz.hlafaille.seizuretracker.model.form.auth.LoginFormModel;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;
import xyz.hlafaille.seizuretracker.service.AuthService;
import xyz.hlafaille.seizuretracker.service.SessionService;
import xyz.hlafaille.seizuretracker.service.UserService;

import java.util.UUID;

@Controller
public class LoginSignupController {
    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public LoginSignupController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    /**
     * Basic redirect to /login
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    /**
     * Log in page
     */
    @GetMapping("/login")
    public String login(@RequestParam(required = false) boolean newAccount, HttpServletRequest request, Model model) {
        boolean isSessionResumable = sessionService.isSessionResumableByBrowserCookies(request.getCookies());
        if (isSessionResumable) {
            return "redirect:/home";
        }
        // todo maybe remove -> model.addAttribute("newAccount", newAccount);
        return "pages/login";
    }

    /**
     * Log in the user by establishing them a new session, setting that cookie and then redirecting the user to /home
     */
    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginFormModel formData, HttpServletResponse response) {
        // start the session
        // todo finish writing UserService before dealing with this
        UUID sessionId = authService.beginSession(formData.getEmailAddress(), formData.getPassword());

        // set the cookie
        Cookie sessionCookie = new Cookie("session", sessionId.toString());
        response.addCookie(sessionCookie);

        return "redirect:/home";
    }

    /**
     * Signup page
     */
    @GetMapping("/signup")
    public String signup(HttpServletRequest request) {
        boolean isSessionResumable = sessionService.isSessionResumableByBrowserCookies(request.getCookies());
        if (isSessionResumable) {
            return "redirect:/home";
        }
        return "redirect:/signup";
    }

    /**
     * Create a new user with data submitted by the user in the SignupFormModel and redirect them to /login
     */
    @PostMapping("/signup")
    public String doSignup(@ModelAttribute SignupFormModel formData) {
        userService.createUser(formData.getFirstName(), formData.getLastName(), formData.getEmailAddress(), formData.getPassword());
        return "redirect:/login?newAccount=true";
    }

    /**
     * Log out the current user and redirect the user to /login
     */
    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) throws SessionCookieMissingException, SessionEntityMissingException {
        Session session = sessionService.getSessionEntityFromCookie(sessionService.getSessionCookieFromBrowserCookies(request.getCookies()));
        sessionService.endSessionById(session.getId());
        return "redirect:/login";
    }
}
