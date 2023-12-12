package xyz.hlafaille.seizuretracker.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // get the http status code
        Object statusCodeObj = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        int statusCode = Integer.parseInt(statusCodeObj.toString());

        // begin defining our error messages
        String funnyCardSubtitle = "Don't think that should happen...";
        String errorMessage = "Undefined error message";
        String detailedErrorMessage = "(no details on this error)";
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            funnyCardSubtitle = "This page is on vacation. Or just lost. Probably lost.";
            errorMessage = "Page not found";
        }
        else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            funnyCardSubtitle = "Someone let the gremlins into our servers again. We're on it!";
            errorMessage = "An internal server error has occurred. Please create a bug report.";
            detailedErrorMessage = throwable.getMessage();
        }

        // add these fields to the model, so they're accessible in the template
        model.addAttribute("funnyCardSubtitle", funnyCardSubtitle);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("detailedErrorMessage", detailedErrorMessage);
        return "views/error";
    }
}
