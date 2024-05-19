package com.andrewsalygin.dto.surgeries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class SurgeryFullInfoDTO {
    private final Integer surgeryId;
    private final Integer patientId;
    private final OffsetDateTime scheduledDateTime;
    private final OffsetDateTime startTime;
    private final OffsetDateTime endTime;
    private final String surgeryType;
    private final String surgicalProcedureDescription;
    private final Boolean emergency;
}
