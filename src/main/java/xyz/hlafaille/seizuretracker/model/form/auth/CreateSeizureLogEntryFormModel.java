package xyz.hlafaille.seizuretracker.model.form.auth;

import java.time.ZonedDateTime;
import java.util.Date;
import lombok.Data;

@Data
public class CreateSeizureLogEntryFormModel {

    private Integer severity;
    private String begin;
    private String end;
    private Integer duration;
    private String beforeNote;
    private String duringNote;
    private String afterNote;
    private String additionalComment;
    private boolean hospitalVisitOccurred;
}
