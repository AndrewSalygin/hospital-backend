package com.andrewsalygin.service.interfaces;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicationFullInfo;
import com.andrewsalygin.hospital.model.MedicationWithoutId;
import com.andrewsalygin.hospital.model.NewInfoMedication;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface MedicationsService {

    ResponseEntity<Void> setNewMedicationFeatures(Integer medicationId, NewInfoMedication newInfoMedication);

    ResponseEntity<IdResponse> addMedication(MedicationWithoutId medicationWithoutId);

    ResponseEntity<Void> deleteMedication(Integer medicationId);

    ResponseEntity<List<MedicationFullInfo>> getMedications(Integer limit, Integer offset);

    ResponseEntity<MedicationFullInfo> getMedication(Integer medicationId);
}
