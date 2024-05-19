package com.andrewsalygin.repository;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.dto.medication.MedicationFullInfoDTO;
import com.andrewsalygin.dto.medication.MedicationWithoutIdDTO;
import com.andrewsalygin.dto.medication.NewInfoMedicationDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MedicationsRepository {

    void setNewMedicationFeatures(Integer medicationId, NewInfoMedicationDTO newInfoMedication);

    IdResponse addMedication(MedicationWithoutIdDTO medicationWithoutId);

    void deleteMedication(Integer medicationId);

    List<MedicationFullInfoDTO> getMedications(Integer limit, Integer offset);

    MedicationFullInfoDTO getMedication(Integer medicationId);
}
