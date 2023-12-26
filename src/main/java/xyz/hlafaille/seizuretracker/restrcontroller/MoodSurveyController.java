package xyz.hlafaille.seizuretracker.restrcontroller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.CreateMoodSurveyEntryRequest;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.CreateMoodSurveyEntryResponse;

import java.util.UUID;

@RestController
@RequestMapping("/v1/moodSurvey")
public class MoodSurveyController {
    @PostMapping("")
    public ResponseEntity<CreateMoodSurveyEntryResponse> createMoodSurveyEntry(@RequestBody CreateMoodSurveyEntryRequest requestBody) {
        return ResponseEntity
                .status(201)
                .body(new CreateMoodSurveyEntryResponse(UUID.randomUUID()));
    }
}