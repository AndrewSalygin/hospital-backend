package com.andrewsalygin.dto.surgeries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SurgeryShortInfoDTO {
    private final Integer surgeryId;
    private final Integer patientId;
    private final OffsetDateTime scheduledDateTime;
    private final String surgeryType;
}
