package xyz.hlafaille.seizuretracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.dto.moodsurvey.MoodSurveyAllowedMoods;
import xyz.hlafaille.seizuretracker.entity.MoodSurvey;
import xyz.hlafaille.seizuretracker.entity.User;
import xyz.hlafaille.seizuretracker.repository.MoodSurveyRepository;
import xyz.hlafaille.seizuretracker.repository.UserRepository;

import java.util.UUID;

@Service
public class MoodSurveyServiceImpl implements MoodSurveyService{
    private final MoodSurveyRepository moodSurveyRepository;
    private final UserRepository userRepository;
    public MoodSurveyServiceImpl(MoodSurveyRepository moodSurveyRepository, UserRepository userRepository) {
        this.moodSurveyRepository = moodSurveyRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a Mood Survey Entry
     * @param mood A member of MoodSurveyAllowedMoods
     * @param user The currently authenticated user
     * @return UUID of the newly created Mood Survey Entry
     */
    @Override
    public UUID createMoodSurveyEntry(MoodSurveyAllowedMoods mood, User user) {
        // First, save the User entity if it's new
        if (user.getId() == null) {
            user = userRepository.save(user);
        }
        MoodSurvey moodSurvey = new MoodSurvey();
        UUID moodSurveyEntryId = UUID.randomUUID();
        moodSurvey.setId(moodSurveyEntryId);
        moodSurvey.setUser(user);
        moodSurvey.setMood(mood);
        moodSurveyRepository.save(moodSurvey);
        return moodSurveyEntryId;
    }
}
