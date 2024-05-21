package com.andrewsalygin.repository.jdbc;

import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.dto.medicalProcedure.MedicalProcedureWithoutIdDTO;
import com.andrewsalygin.repository.interfaces.MedicalProceduresRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcMedicalProceduresRepository implements MedicalProceduresRepository {

    private final JdbcClient client;

    @Override
    public List<MedicalProcedureFullInfoDTO> getMedicalProcedures(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT medicalProcedureId, medicalProcedureName, price FROM medicalProcedure " +
                "WHERE isDeleted = 0 ORDER BY medicalProcedureId " +
                "OFFSET :offset ROWS";
        } else {
            query = "SELECT medicalProcedureId, medicalProcedureName, price FROM medicalProcedure " +
                "WHERE isDeleted = 0 ORDER BY medicalProcedureId " +
                "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }
        return client.sql(query)
            .param("limit", limit)
            .param("offset", offset)
            .query(MedicalProcedureFullInfoDTO.class)
            .list();
    }

    @Override
    public MedicalProcedureFullInfoDTO getMedicalProcedure(Integer medicalProcedureId) {
        return client.sql("SELECT medicalProcedureId, medicalProcedureName, price FROM medicalProcedure " +
                "WHERE isDeleted = 0 AND medicalProcedureId = ?")
            .param(medicalProcedureId)
            .query(MedicalProcedureFullInfoDTO.class)
            .single();
    }

    @Override
    public Integer addMedicalProcedure(MedicalProcedureWithoutIdDTO medicalProcedureWithoutId) {
        return client.sql("INSERT INTO medicalProcedure (medicalProcedureName, price) " +
                "OUTPUT INSERTED.medicalProcedureId VALUES (?, ?)")
            .param(medicalProcedureWithoutId.getMedicalProcedureName())
            .param(medicalProcedureWithoutId.getPrice())
            .query(Integer.class)
            .single();
    }

    @Override
    public void editMedicalProcedure(
        Integer medicalProcedureId,
        MedicalProcedureWithoutIdDTO medicalProcedureWithoutId
    ) {
        client.sql("UPDATE medicalProcedure SET medicalProcedureName = ?, price = ? WHERE isDeleted = 0 " +
                "AND medicalProcedureId = ?")
            .param(medicalProcedureWithoutId.getMedicalProcedureName())
            .param(medicalProcedureWithoutId.getPrice())
            .param(medicalProcedureId)
            .update();
    }

    @Override
    public void deleteMedicalProcedure(Integer medicalProcedureId) {
        client.sql("UPDATE medicalProcedure SET isDeleted = 1 WHERE medicalProcedureId = ?")
            .param(medicalProcedureId)
            .update();
    }
}
