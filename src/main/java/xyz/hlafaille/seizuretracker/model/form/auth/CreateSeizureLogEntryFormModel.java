package xyz.hlafaille.seizuretracker.model.form.auth;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreateSeizureLogEntryFormModel {
    private Integer severity;
    private ZonedDateTime begin;
    private ZonedDateTime end;
    private String beforeNote;
    private String duringNote;
    private String afterNote;
    private String additionalComment;
}
