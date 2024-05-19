package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.MedicationFullInfo;
import com.andrewsalygin.hospital.model.MedicationWithoutId;
import com.andrewsalygin.hospital.model.NewInfoMedication;
import com.andrewsalygin.repository.interfaces.MedicationsRepository;
import com.andrewsalygin.service.interfaces.MedicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcMedicationsService implements MedicationsService {

    private final MedicationsRepository medicationsRepository;

    @Override
    public ResponseEntity<Void> setNewMedicationFeatures(Integer medicationId, NewInfoMedication newInfoMedication) {
        return null;
    }

    @Override
    public ResponseEntity<IdResponse> addMedication(MedicationWithoutId medicationWithoutId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteMedication(Integer medicationId) {
        return null;
    }

    @Override
    public ResponseEntity<List<MedicationFullInfo>> getMedications(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<MedicationFullInfo> getMedication(Integer medicationId) {
        return null;
    }
}
