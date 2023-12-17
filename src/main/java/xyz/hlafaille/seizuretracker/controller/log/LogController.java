package xyz.hlafaille.seizuretracker.controller.log;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;
import xyz.hlafaille.seizuretracker.entity.Session;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.exception.SessionCookieMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionEntityMissingException;
import xyz.hlafaille.seizuretracker.exception.SessionUserMissingException;
import xyz.hlafaille.seizuretracker.service.SeizureLogService;
import xyz.hlafaille.seizuretracker.service.SessionService;

@Controller
public class LogController {

    private final SessionService sessionService;
    private final SeizureLogService seizureLogService;

    @Autowired
    public LogController(SessionService sessionService, SeizureLogService seizureLogService) {
        this.sessionService = sessionService;
        this.seizureLogService = seizureLogService;
    }

    @GetMapping("/logDashboard")
    public String log(HttpServletRequest request, Model model)
        throws SessionCookieMissingException, SessionEntityMissingException, SessionUserMissingException {
        // get the user by their session id
        Cookie sessionCookie = sessionService.getSessionCookieFromBrowserCookies(request.getCookies());
        Session session = sessionService.getSessionEntityFromCookie(sessionCookie);
        User user = sessionService.getUserEntityFromSessionId(session.getId());
        model.addAttribute("userFirstName", user.getFirstName());

        // get the recorded logs
        List<SeizureLog> seizureLogs = seizureLogService.getSeizureLogEntriesByUserId(user.getId());
        model.addAttribute("seizureLogs", seizureLogs);
        return "views/log/log_dashboard";
    }
    /*@GetMapping("/log/newEntry")
    public String logNewEntry(HttpServletRequest request, Model model) throws SessionCookieMissingException, SessionEntityMissingException, SessionUserMissingException {
        // get the user by their session id
        Cookie sessionCookie = sessionService.getSessionCookieFromBrowserCookies(request.getCookies());
        Session session = sessionService.getSessionEntityFromCookie(sessionCookie);
        User user = sessionService.getUserEntityFromSessionId(session.getId());
        model.addAttribute("userFirstName", user.getFirstName());
        return "pages/log_new";
    }

    @PostMapping("/log/newEntry")
    public String doLogNewEntry(HttpServletRequest request, CreateSeizureLogEntryFormModel formData, Model model) throws SessionCookieMissingException, SessionEntityMissingException, SessionUserMissingException {
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
        return "redirect:/log?entryCreated=true";
    }*/
}
