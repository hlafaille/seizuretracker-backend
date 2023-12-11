package xyz.hlafaille.seizuretracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TkTestController {
    @GetMapping("/tk/test")
    public String getTkTestPage() {
        return "views/tk_test_page";
    }
}
