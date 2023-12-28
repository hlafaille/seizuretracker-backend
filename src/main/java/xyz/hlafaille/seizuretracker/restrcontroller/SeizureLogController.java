package xyz.hlafaille.seizuretracker.restrcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hlafaille.seizuretracker.dto.seizurelog.GetSeizureLogEntryByIdResponse;
import xyz.hlafaille.seizuretracker.entity.User;

import java.util.UUID;

@RestController
@RequestMapping("/seizureLog")
public class SeizureLogController {
    @GetMapping("/entry/{logEntryId}")
    public ResponseEntity<GetSeizureLogEntryByIdResponse> getSeizureLogEntryById(@PathVariable UUID logEntryId, User user) {

        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(new GetSeizureLogEntryByIdResponse());
    }
}
