package com.andrewsalygin.controller.patients;

import com.andrewsalygin.hospital.api.SuperAdminPatientsApi;
import com.andrewsalygin.service.interfaces.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminPatientsController implements SuperAdminPatientsApi {

    private final PatientsService patientsService;

    @Override
    public ResponseEntity<Void> deletePatient(Integer id) {

        return patientsService.deletePatient(id);
    }
}
