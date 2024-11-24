package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.dto.medication.MedicationFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.medication.MedicationWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.medication.NewInfoMedicationDTO;
import java.util.List;

public interface MedicationsRepository {

    void setNewMedicationFeatures(Integer medicationId, NewInfoMedicationDTO newInfoMedication);

    Integer addMedication(MedicationWithoutIdDTO medicationWithoutId);

    void deleteMedication(Integer medicationId);

    List<MedicationFullInfoDTO> getMedications(Integer limit, Integer offset);

    MedicationFullInfoDTO getMedication(Integer medicationId);
}
