package com.andrewsalygin.controller.medications;

import com.andrewsalygin.hospital.api.AdminMedicationsApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicationWithoutId;
import com.andrewsalygin.hospital.model.NewInfoMedication;
import com.andrewsalygin.service.MedicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AdminMedicationsController implements AdminMedicationsApi {

    private final MedicationsService medicationsService;

    @Override
    public ResponseEntity<Void> setNewMedicationFeatures(Integer medicationId, NewInfoMedication newInfoMedication) {
        return medicationsService.setNewMedicationFeatures(medicationId, newInfoMedication);
    }

    @Override
    public ResponseEntity<IdResponse> addMedication(MedicationWithoutId medicationWithoutId) {
        return medicationsService.addMedication(medicationWithoutId);
    }

    @Override
    public ResponseEntity<Void> deleteMedication(Integer medicationId) {
        return medicationsService.deleteMedication(medicationId);
    }
}
