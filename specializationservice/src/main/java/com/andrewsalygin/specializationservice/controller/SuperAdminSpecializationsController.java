package com.andrewsalygin.specializationservice.controller;

import com.andrewsalygin.specializationservice.api.SuperAdminSpecializationsApi;
import com.andrewsalygin.specializationservice.model.IdResponse;
import com.andrewsalygin.specializationservice.service.JdbcSpecializationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuperAdminSpecializationsController implements SuperAdminSpecializationsApi {

    private final JdbcSpecializationsService specializationsService;

    @Override
    public ResponseEntity<IdResponse> addSpecialization(String specializationName) {
        return specializationsService.addSpecialization(specializationName);
    }

    @Override
    public ResponseEntity<Void> deleteSpecialization(Integer specializationId) {
        return specializationsService.deleteSpecialization(specializationId);
    }
}
