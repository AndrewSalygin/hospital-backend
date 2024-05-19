package com.andrewsalygin.controller.specializations;

import com.andrewsalygin.hospital.api.SpecializationsApi;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.service.RecipesService;
import com.andrewsalygin.service.SpecializationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SpecializationsController implements SpecializationsApi {

    private final SpecializationsService specializationsService;

    @Override
    public ResponseEntity<List<Specialization>> getSpecializations() {
        return specializationsService.getSpecializations();
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(Integer specializationId) {
        return specializationsService.getSpecializationsDoctors(specializationId);
    }
}
