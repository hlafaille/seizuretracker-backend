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

    @GetMapping("/log/newDraft")
    public String createLogDraft(HttpServletRequest request, Model model) {
        return "redirect:/log/draft/%s/initialize/step1".formatted(1234);
    }

    @GetMapping("/log/draft/{entryId}/initialize/step1")
    public String logDraftInitializeStep1(
        HttpServletRequest request,
        Model model
    ) {
        return "pages/log/log_new_step1";
    }

    @GetMapping("/log/draft/{entryId}/initialize/step2")
    public String logDraftInitializeStep2(
        HttpServletRequest request,
        Model model
    ) {
        return "pages/log/log_new_step2";
    }

    @GetMapping("/log/draft/{entryId}/initialize/step3")
    public String logDraftInitializeStep3(
        HttpServletRequest request,
        Model model
    ) {
        return "pages/log/log_new_step3";
    }
}
