package com.andrewsalygin.applicationservice.service.interfaces;

import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.MedicationFullInfo;
import com.andrewsalygin.applicationservice.model.MedicationWithoutId;
import com.andrewsalygin.applicationservice.model.NewInfoMedication;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MedicationsService {

    ResponseEntity<Void> setNewMedicationFeatures(Integer medicationId, NewInfoMedication newInfoMedication);

    ResponseEntity<IdResponse> addMedication(MedicationWithoutId medicationWithoutId);

    ResponseEntity<Void> deleteMedication(Integer medicationId);

    ResponseEntity<List<MedicationFullInfo>> getMedications(Integer limit, Integer offset);

    ResponseEntity<MedicationFullInfo> getMedication(Integer medicationId);
}
