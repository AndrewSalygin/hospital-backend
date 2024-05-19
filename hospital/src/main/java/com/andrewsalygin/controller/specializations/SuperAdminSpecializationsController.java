package com.andrewsalygin.controller.specializations;

import com.andrewsalygin.hospital.api.SuperAdminSpecializationsApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.service.interfaces.SpecializationsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminSpecializationsController implements SuperAdminSpecializationsApi {

    private final SpecializationsService specializationsService;

    @Override
    public ResponseEntity<List<IdResponse>> addSpecialization(String specializationName) {
        return specializationsService.addSpecialization(specializationName);
    }

    @Override
    public ResponseEntity<Void> deleteSpecialization(Integer specializationId) {
        return specializationsService.deleteSpecialization(specializationId);
    }
}
