package xyz.hlafaille.seizuretracker.controller.log;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionUserMissingException;
import xyz.hlafaille.seizuretracker.model.form.auth.CreateSeizureLogEntryFormModel;
import xyz.hlafaille.seizuretracker.service.SeizureLogService;
import xyz.hlafaille.seizuretracker.service.SessionService;

@Controller
public class LogEntryController {

    private final SessionService sessionService;
    private final SeizureLogService seizureLogService;

    @Autowired
    public LogEntryController(SessionService sessionService, SeizureLogService seizureLogService) {
        this.sessionService = sessionService;
        this.seizureLogService = seizureLogService;
    }

    @GetMapping("/logDashboard/newEntry")
    public String createLogEntry(HttpServletRequest request, Model model) {
        return "views/log/log_new";
    }

    @GetMapping("/logDashboard/newEntry/stepTwo")
    public String createLogEntryStepTwo(HttpServletRequest request, Model model) {
        return "fragments/log/logNewCards :: _stepTwoContent";
    }

    @GetMapping("/logDashboard/newEntry/stepThree")
    public String createLogEntryStepThree(HttpServletRequest request, Model model) {
        return "fragments/log/logNewCards :: _stepThreeContent";
    }

    @PostMapping("/logDashboard/newEntry")
    public String doLogNewEntry(
        HttpServletRequest request,
        CreateSeizureLogEntryFormModel formData,
        Model model,
        HttpServletResponse response
    ) throws SessionEntityMissingException, SessionUserMissingException, SessionCookieMissingException {
        // get the user by their session id
        Cookie sessionCookie = sessionService.getSessionCookieFromBrowserCookies(request.getCookies());
        Session session = sessionService.getSessionEntityFromCookie(sessionCookie);
        User user = sessionService.getUserEntityFromSessionId(session.getId());
        seizureLogService.createLogEntry(
            formData.getSeverity(),
            user.getId(),
            formData.getDuration(),
            formData.getBeforeNote(),
            formData.getDuringNote(),
            formData.getAfterNote(),
            formData.isHospitalVisitOccurred(),
            formData.getAdditionalComment(),
            false
        );
        response.addHeader("hx-redirect", "/logDashboard");
        return "";
    }
}
