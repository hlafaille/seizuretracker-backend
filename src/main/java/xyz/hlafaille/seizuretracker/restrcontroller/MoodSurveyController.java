package xyz.hlafaille.seizuretracker.restrcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.CreateMoodSurveyEntryResponse;

@RestController
@RequestMapping("/v1/moodSurvey")
public class MoodSurveyController {
    @PostMapping("")
    public CreateMoodSurveyEntryResponse createMoodSurveyEntry(@RequestBody) {

    }
}
