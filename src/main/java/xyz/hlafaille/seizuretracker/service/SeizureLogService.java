package xyz.hlafaille.seizuretracker.service;

import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public interface SeizureLogService {
    /**
     * Create a new Seizure Log Entry
     * @param severity Severity level, where 1 is the best and 10 is the worst.
     * @param userId User UUID
     * @param seizureBegin Date/time of when the seizure began
     * @param seizureEnd Date/time of when the seizure ended
     * @param beforeSeizureNote Notes before the seizure
     * @param duringSeizureNote Notes during the seizure
     * @param afterSeizureNote Notes after the seizure
     * @param hospitalVisitOccurred If a hospital visit occurred
     * @param additionalComment Any additional comments
     * @return UUID of the seizure log entry
     */
    UUID createLogEntry(
            Integer severity, UUID userId, ZonedDateTime seizureBegin, ZonedDateTime seizureEnd, String beforeSeizureNote,
            String duringSeizureNote, String afterSeizureNote, boolean hospitalVisitOccurred, String additionalComment
    );

    /**
     * Get all seizure log entries for a particular user
     * @param userId User UUID
     * @return List of SeizureLog entities
     */
    List<SeizureLog> getSeizureLogEntriesByUserId(UUID userId);
}
