package com.andrewsalygin.applicationservice.controller.medications;

import com.andrewsalygin.applicationservice.api.MedicationsApi;
import com.andrewsalygin.applicationservice.model.MedicationFullInfo;
import com.andrewsalygin.applicationservice.service.interfaces.MedicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedicationsController implements MedicationsApi {

    private final MedicationsService medicationsService;

    @Override
    public ResponseEntity<List<MedicationFullInfo>> getMedications(Integer limit, Integer offset) {
        return medicationsService.getMedications(limit, offset);
    }

    @Override
    public ResponseEntity<MedicationFullInfo> getMedication(Integer medicationId) {
        return medicationsService.getMedication(medicationId);
    }
}
