package xyz.hlafaille.seizuretracker.service;

import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.MoodSurveyAllowedMoods;
import xyz.hlafaille.seizuretracker.entity.MoodSurvey;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.repository.MoodSurveyRepository;

import java.util.UUID;

@Service
public interface MoodSurveyService{

    /**
     * Create a new Mood Survey Entry
     * @param mood A member of MoodSurveyAllowedMoods
     * @param user The currently authenticated user
     * @return UUID of the newly created Mood Survey entry
     */
    UUID createMoodSurveyEntry(MoodSurveyAllowedMoods mood, User user);
}
