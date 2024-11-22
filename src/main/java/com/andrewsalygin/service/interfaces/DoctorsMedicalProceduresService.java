package com.andrewsalygin.service.interfaces;

import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.MedicalProcedureFullInfo;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface DoctorsMedicalProceduresService {

    ResponseEntity<List<DoctorShortInfo>> getDoctorsForProcedure(Integer procedureId);

    ResponseEntity<List<MedicalProcedureFullInfo>> getProceduresForDoctor(Integer doctorId);

    ResponseEntity<Void> addDoctorProcedure(Integer doctorId, Integer procedureId);

    ResponseEntity<Void> deleteDoctorProcedure(Integer doctorId, Integer procedureId);
}
