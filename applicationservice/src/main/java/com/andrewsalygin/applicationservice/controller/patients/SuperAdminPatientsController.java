package com.andrewsalygin.applicationservice.controller.patients;

import com.andrewsalygin.applicationservice.api.SuperAdminPatientsApi;
import com.andrewsalygin.applicationservice.service.interfaces.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperAdminPatientsController implements SuperAdminPatientsApi {

    private final PatientsService patientsService;

    @Override
    public ResponseEntity<Void> deletePatient(Integer id) {

        return patientsService.deletePatient(id);
    }
}
