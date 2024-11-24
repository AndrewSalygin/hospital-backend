package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureWithoutIdDTO;
import java.util.List;

public interface MedicalProceduresRepository {

    List<MedicalProcedureFullInfoDTO> getMedicalProcedures(Integer limit, Integer offset);

    MedicalProcedureFullInfoDTO getMedicalProcedure(Integer medicalProcedureId);

    Integer addMedicalProcedure(MedicalProcedureWithoutIdDTO medicalProcedureWithoutId);

    void editMedicalProcedure(Integer medicalProcedureId, MedicalProcedureWithoutIdDTO medicalProcedureWithoutId);

    void deleteMedicalProcedure(Integer medicalProcedureId);
}
