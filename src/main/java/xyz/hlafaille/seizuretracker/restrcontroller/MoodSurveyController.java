package xyz.hlafaille.seizuretracker.restrcontroller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.CreateMoodSurveyEntryRequest;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.CreateMoodSurveyEntryResponse;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.service.MoodSurveyService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/moodSurvey")
public class MoodSurveyController {
    private final MoodSurveyService moodSurveyService;

    public MoodSurveyController(MoodSurveyService moodSurveyService) {
        this.moodSurveyService = moodSurveyService;
    }

    @PostMapping("")
    public ResponseEntity<CreateMoodSurveyEntryResponse> createMoodSurveyEntry(
            @RequestBody CreateMoodSurveyEntryRequest requestBody,
            User user
    ) {
        UUID moodSurveyEntryId = moodSurveyService.createMoodSurveyEntry(
                requestBody.getCurrentMood(),
                user
        );
        return ResponseEntity
                .status(201)
                .body(new CreateMoodSurveyEntryResponse(UUID.randomUUID()));
    }
}