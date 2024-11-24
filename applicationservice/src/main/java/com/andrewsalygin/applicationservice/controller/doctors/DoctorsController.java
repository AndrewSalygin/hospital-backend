package com.andrewsalygin.applicationservice.controller.doctors;

import com.andrewsalygin.applicationservice.api.DoctorsApi;
import com.andrewsalygin.applicationservice.model.DoctorFullInfo;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import com.andrewsalygin.applicationservice.model.DoctorSpecialization;
import com.andrewsalygin.applicationservice.service.interfaces.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorsController implements DoctorsApi {

    private final DoctorsService doctorsService;

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctors(Integer limit, Integer offset) {
        return doctorsService.getDoctors(limit, offset);
    }

    @Override
    public ResponseEntity<DoctorFullInfo> getDoctor(Integer doctorId) {
        return doctorsService.getDoctor(doctorId);
    }

    @Override
    public ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializations(Integer doctorId) {
        return doctorsService.getDoctorSpecializations(doctorId);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/specializations/{specializationId}/doctors/",
        produces = { "application/json" }
    )
    public ResponseEntity<List<DoctorShortInfo>> getDoctorsForSpecialization(@PathVariable("specializationId") Integer specializationId) {
        return doctorsService.getDoctorsForSpecialization(specializationId);
    }
}
