package xyz.hlafaille.seizuretracker.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import xyz.hlafaille.seizuretracker.entity.SeizureLog;
import xyz.hlafaille.seizuretracker.entity.User;

@Service
public interface SeizureLogService {
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
     * @param isDraft               Is this Log Entry a Draft
     * @return UUID of the seizure log entry
     */
    UUID createLogEntry(
            Integer severity,
            UUID userId,
            Integer duration,
            String beforeSeizureNote,
            String duringSeizureNote,
            String afterSeizureNote,
            boolean hospitalVisitOccurred,
            String additionalComment,
            boolean isDraft
    );

    /**
     * Get all seizure log entries for a particular user
     *
     * @param userId User UUID
     * @return List of SeizureLog entities
     */
    List<SeizureLog> getSeizureLogEntriesByUserId(UUID userId);

    /**
     * Get a single Seizure Log Entry by its UUID
     *
     * @param user       User entity
     * @param logEntryId Log Entry ID
     */
    SeizureLog getSingleSeizureLogEntriesById(User user, UUID logEntryId);
}
