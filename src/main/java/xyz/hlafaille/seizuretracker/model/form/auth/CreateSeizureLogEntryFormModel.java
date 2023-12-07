package xyz.hlafaille.seizuretracker.model.form.auth;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

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
