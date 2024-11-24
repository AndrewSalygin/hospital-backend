package com.andrewsalygin.applicationservice.controller.medications;

import com.andrewsalygin.applicationservice.api.AdminMedicationsApi;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicationWithoutId;
import com.andrewsalygin.applicationservice.model.NewInfoMedication;
import com.andrewsalygin.applicationservice.service.interfaces.MedicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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
