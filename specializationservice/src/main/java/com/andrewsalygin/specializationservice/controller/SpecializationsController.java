package com.andrewsalygin.specializationservice.controller;

import com.andrewsalygin.specializationservice.api.SpecializationsApi;
import com.andrewsalygin.specializationservice.dto.DoctorSpecializationDTO;
import com.andrewsalygin.specializationservice.model.DoctorShortInfo;
import com.andrewsalygin.specializationservice.model.Specialization;
import com.andrewsalygin.specializationservice.service.JdbcSpecializationsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpecializationsController implements SpecializationsApi {

    private final JdbcSpecializationsService specializationsService;

    @Override
    public ResponseEntity<List<Specialization>> getSpecializations() {
        return specializationsService.getSpecializations();
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(Integer specializationId) {
        return specializationsService.getSpecializationsDoctors(specializationId);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/doctors/{doctorId}/specializations/",
        produces = { "application/json" }
    )
    public ResponseEntity<List<DoctorSpecializationDTO>> getDoctorSpecializations(@Parameter(name = "specializationId", description = "Id доктора", required = true, in = ParameterIn.PATH) @PathVariable("doctorId") Integer doctorId) {
        return specializationsService.getDoctorSpecializations(doctorId);
    }
}
