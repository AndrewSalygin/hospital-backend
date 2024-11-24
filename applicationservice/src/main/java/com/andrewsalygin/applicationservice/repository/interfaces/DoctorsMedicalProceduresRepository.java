package com.andrewsalygin.applicationservice.repository.interfaces;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.medicalProcedure.MedicalProcedureFullInfoDTO;
import java.util.List;

public interface DoctorsMedicalProceduresRepository {

    List<DoctorShortInfoDTO> getDoctorsForProcedure(Integer procedureId);

    List<MedicalProcedureFullInfoDTO> getProceduresForDoctor(Integer doctorId);

    void addDoctorProcedure(Integer doctorId, Integer procedureId);

    void deleteDoctorProcedure(Integer doctorId, Integer procedureId);
}
