package com.andrewsalygin.controller.surgeries;

import com.andrewsalygin.hospital.api.SurgeriesApi;
import com.andrewsalygin.hospital.model.MedicationWithAmount;
import com.andrewsalygin.hospital.model.SurgeryFullInfo;
import com.andrewsalygin.service.interfaces.SurgeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SurgeriesController implements SurgeriesApi {

    private final SurgeriesService surgeriesService;

    @Override
    public ResponseEntity<List<SurgeryFullInfo>> getAllSurgeries(Integer limit, Integer offset) {
        return surgeriesService.getAllSurgeries(limit, offset);
    }

    @Override
    public ResponseEntity<List<MedicationWithAmount>> getMedicationsForSurgery(Integer surgeryId) {
        return surgeriesService.getMedicationsForSurgery(surgeryId);
    }

    @Override
    public ResponseEntity<Void> updateSurgeryDescription(Integer surgeryId, String surgicalProcedureDescription) {
        return surgeriesService.updateSurgeryDescription(surgeryId, surgicalProcedureDescription);
    }

    @Override
    public ResponseEntity<List<SurgeryFullInfo>> getSurgeriesByPatient(Integer patientId) {
        return surgeriesService.getSurgeriesByPatient(patientId);
    }

    @Override
    public ResponseEntity<SurgeryFullInfo> getSurgeryById(Integer surgeryId) {
        return surgeriesService.getSurgeryById(surgeryId);
    }
}
