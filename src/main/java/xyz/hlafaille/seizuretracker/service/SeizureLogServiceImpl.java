package xyz.hlafaille.seizuretracker.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;
import xyz.hlafaille.seizuretracker.repository.SeizureLogRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SeizureLogServiceImpl implements SeizureLogService {
    private final SeizureLogRepository seizureLogRepository;
    private final Logger logger = LoggerFactory.getLogger(SeizureLogService.class);

    @Autowired
    public SeizureLogServiceImpl(SeizureLogRepository seizureLogRepository) {
        this.seizureLogRepository = seizureLogRepository;
    }

    /**
     * Create a new Seizure Log Entry
     *
     * @param severity              Severity level, where 1 is the best and 10 is the worst.
     * @param userId                User UUID
     * @param duration              Duration of the seizure in minutes
     * @param beforeSeizureNote     Notes before the seizure
     * @param duringSeizureNote     Notes during the seizure
     * @param afterSeizureNote      Notes after the seizure
     * @param hospitalVisitOccurred If a hospital visit occurred
     * @param additionalComment     Any additional comments
     * @return UUID of the seizure log entry
     */
    @Override
    @Transactional
    public UUID createLogEntry(Integer severity, UUID userId, Integer duration, String beforeSeizureNote, String duringSeizureNote, String afterSeizureNote, boolean hospitalVisitOccurred, String additionalComment, boolean isDraft) {
        SeizureLog seizureLog = new SeizureLog();
        UUID seizureLogId = UUID.randomUUID();
        seizureLog.setId(seizureLogId);
        seizureLog.setSeverity(severity);
        seizureLog.setUser(userId);
        seizureLog.setDuration(duration);
        seizureLog.setBeforeSeizureNote(beforeSeizureNote);
        seizureLog.setDuringSeizureNote(duringSeizureNote);
        seizureLog.setAfterSeizureNote(afterSeizureNote);
        seizureLog.setHospitalVisitOccurred(hospitalVisitOccurred);
        seizureLog.setAdditionalComment(additionalComment);
        seizureLog.setDraft(isDraft);
        logger.info("created seizure log entry: %s".formatted(seizureLogId.toString()));
        seizureLogRepository.save(seizureLog);
        return seizureLogId;
    }

    /**
     * Get all seizure log entries for a particular user
     *
     * @param userId User UUID
     * @return List of SeizureLog entities
     */
    @Override
    public List<SeizureLog> getSeizureLogEntriesByUserId(UUID userId) {
        return seizureLogRepository.findAllByUser(userId);
    }
}
