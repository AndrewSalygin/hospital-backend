package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.medication.MedicationFullInfoDTO;
import com.andrewsalygin.dto.medication.MedicationWithoutIdDTO;
import com.andrewsalygin.dto.medication.NewInfoMedicationDTO;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.repository.interfaces.MedicationsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcMedicationsRepository implements MedicationsRepository {

    private final JdbcClient client;

    @Override
    public void setNewMedicationFeatures(Integer medicationId, NewInfoMedicationDTO newInfoMedication) {

    }

    @Override
    public IdResponse addMedication(MedicationWithoutIdDTO medicationWithoutId) {
        return null;
    }

    @Override
    public void deleteMedication(Integer medicationId) {

    }

    @Override
    public List<MedicationFullInfoDTO> getMedications(Integer limit, Integer offset) {
        return List.of();
    }

    @Override
    public MedicationFullInfoDTO getMedication(Integer medicationId) {
        return null;
    }
}
