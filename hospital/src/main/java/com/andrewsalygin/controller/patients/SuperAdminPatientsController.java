package com.andrewsalygin.controller.patients;

import com.andrewsalygin.hospital.api.SuperApi;
import org.springframework.http.ResponseEntity;

public class SuperAdminPatientsController implements SuperApi {

    @Override
    public ResponseEntity<Void> deletePatient(Integer id) {
        return patientsService.deletePatient(id);
    }
}
