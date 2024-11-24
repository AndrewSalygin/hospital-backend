package com.andrewsalygin.applicationservice.repository.jdbc;

import com.andrewsalygin.applicationservice.repository.interfaces.DiseasesRepository;
import com.andrewsalygin.applicationservice.dto.disease.DiseaseFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.disease.DiseaseWithoutIdDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.patient.PatientShortInfoDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcDiseasesRepository implements DiseasesRepository {

    private final JdbcClient client;

    @Override
    public List<DiseaseFullInfoDTO> getDiseases(Integer limit, Integer offset) {
        String query = "";
        if (limit == -1) {
            query = "SELECT diseaseId, diseaseName, diseaseCode, commonDiseaseDuration " +
                "FROM disease " +
                "ORDER BY diseaseId " +
                "OFFSET :offset ROWS";
        } else if (limit > 0) {
            query = "SELECT diseaseId, diseaseName, diseaseCode, commonDiseaseDuration " +
                "FROM disease " +
                "ORDER BY diseaseId " +
                "OFFSET :offset ROWS " +
                "FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(DiseaseFullInfoDTO.class)
            .list();
    }

    @Override
    public DiseaseFullInfoDTO getDisease(Integer diseaseId) {
        return client.sql("SELECT diseaseId, diseaseName, diseaseCode, commonDiseaseDuration " +
                "FROM disease " +
                "WHERE diseaseId = ?")
            .param(diseaseId)
            .query(DiseaseFullInfoDTO.class)
            .single();
    }

    @Override
    public List<PatientShortInfoDTO> getPatientsWithDisease(Integer diseaseId) {
        return client.sql("SELECT p.patientId, p.lastName, p.firstName, p.middleName, p.gender, p.dateOfBirth, " +
                "p.isDeleted " +
                "FROM patient p " +
                "INNER JOIN patientDiseasesJournal pdj ON p.patientId = pdj.patientId " +
                "WHERE pdj.diseaseId = ?")
            .param(diseaseId)
            .query(PatientShortInfoDTO.class)
            .list();
    }

    @Override
    public void addDisease(DiseaseWithoutIdDTO diseaseWithoutId) {
        client.sql("INSERT INTO disease (diseaseName, diseaseCode, commonDiseaseDuration) VALUES (?, ?, ?)")
            .params(
                diseaseWithoutId.getDiseaseName(),
                diseaseWithoutId.getDiseaseCode(),
                diseaseWithoutId.getCommonDiseaseDuration()
            )
            .update();
    }

    @Override
    public void editDisease(Integer diseaseId, DiseaseWithoutIdDTO diseaseWithoutId) {
        client.sql("UPDATE disease SET diseaseName = ?, diseaseCode = ?, commonDiseaseDuration = ? WHERE diseaseId = ?")
            .params(
                diseaseWithoutId.getDiseaseName(),
                diseaseWithoutId.getDiseaseCode(),
                diseaseWithoutId.getCommonDiseaseDuration(),
                diseaseId
            )
            .update();
    }
}
