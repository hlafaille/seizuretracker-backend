package xyz.hlafaille.seizuretracker.controller.log;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.hlafaille.seizuretracker.service.SeizureLogService;
import xyz.hlafaille.seizuretracker.service.SessionService;

@Controller
public class NewLogEntryController {
    private final SessionService sessionService;
    private final SeizureLogService seizureLogService;

    @Autowired
    public NewLogEntryController(SessionService sessionService, SeizureLogService seizureLogService) {
        this.sessionService = sessionService;
        this.seizureLogService = seizureLogService;
    }

    @GetMapping("/log/newEntry")
    public String log(HttpServletRequest request, Model model) {
        return "";
    }
}
