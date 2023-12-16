package xyz.hlafaille.seizuretracker.controller.log;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.hlafaille.seizuretracker.service.SeizureLogService;
import xyz.hlafaille.seizuretracker.service.SessionService;

@Controller
public class LogEntryController {

    private final SessionService sessionService;
    private final SeizureLogService seizureLogService;

    @Autowired
    public LogEntryController(
        SessionService sessionService,
        SeizureLogService seizureLogService
    ) {
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
}
