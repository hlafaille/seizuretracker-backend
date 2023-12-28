package xyz.hlafaille.seizuretracker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateNewAccountResponse {
    private UUID userId;
}
