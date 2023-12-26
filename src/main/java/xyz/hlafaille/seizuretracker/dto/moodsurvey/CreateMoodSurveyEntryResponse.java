package xyz.hlafaille.seizuretracker.dto.moodsurvey;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateMoodSurveyEntryResponse {
    private UUID moodSurveyEntryId;
}
