package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.UserEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.UserPasswordMismatchException;
import xyz.hlafaille.seizuretracker.model.form.auth.LoginFormModel;
import xyz.hlafaille.seizuretracker.model.form.auth.SignupFormModel;
import xyz.hlafaille.seizuretracker.service.SeizureLogService;
import xyz.hlafaille.seizuretracker.service.SessionService;
import xyz.hlafaille.seizuretracker.service.UserService;

import java.util.UUID;

@Controller
public class LoginSignupController {
    private final UserService userService;
    private final SessionService sessionService;
    private final Logger logger = LoggerFactory.getLogger(SeizureLogService.class);

    /*@ExceptionHandler({UserEntityMissingException.class})
    public String handleUserEntityMissingException(Model model){
        model.addAttribute("userMissing", true);
        return "redirect:/login?userMissing=true";
    }*/

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
    public String login(HttpServletRequest request, Model model, HttpServletResponse response) {
        boolean isSessionResumable = sessionService.isSessionResumableByBrowserCookies(request.getCookies());
        if (isSessionResumable) {
            logger.info("session is resumable, redirecting home");
            response.addHeader("hx-redirect", "/home");
            response.addHeader("hx-replace-url", "true");
            return "redirect:/home";
        }
        return "views/login";
    }

    /**
     * Log in the user by establishing them a new session, setting that cookie and then redirecting the user to /home
     */
    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginFormModel formData, Model model, HttpServletResponse response) throws UserPasswordMismatchException {
        // start the session
        UUID sessionId;
        try {
            sessionId = sessionService.beginSession(formData.getEmailAddress(), formData.getPassword());
        } catch (UserEntityMissingException | UserPasswordMismatchException e) {
            model.addAttribute("userNotFound", true);
            return "fragments/auth/logInCard :: logInCard";
        }

        // set the cookie
        Cookie sessionCookie = new Cookie("session", sessionId.toString());
        response.addCookie(sessionCookie);
        response.addHeader("hx-redirect", "/home");
        response.addHeader("hx-replace-url", "true");
        return "";
    }

    /**
     * Signup page
     */
    @GetMapping("/signup")
    public String signup(HttpServletRequest request) {
        boolean isSessionResumable = sessionService.isSessionResumableByBrowserCookies(request.getCookies());
        if (isSessionResumable) {
            logger.info("session is resumable, redirecting home");
            return "redirect:/home";
        }
        return "views/signup";
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
    public String doLogout(HttpServletRequest request) throws
            SessionCookieMissingException, SessionEntityMissingException {
        Session session = sessionService.getSessionEntityFromCookie(sessionService.getSessionCookieFromBrowserCookies(request.getCookies()));
        sessionService.endSessionById(session.getId());
        return "redirect:/login";
    }
}
