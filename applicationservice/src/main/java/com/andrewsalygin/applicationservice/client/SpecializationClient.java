package com.andrewsalygin.applicationservice.client;

import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.applicationservice.model.Specialization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(name = "specialization-service", url = "${application.specialization-service.url}")
public interface SpecializationClient {

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/doctors/{doctorId}/specializations/",
        produces = { "application/json" }
    )
    ResponseEntity<List<DoctorSpecializationDTO>> getDoctorSpecializations(@PathVariable("doctorId") Integer doctorId);

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/specializations",
        produces = { "application/json" }
    )
    ResponseEntity<List<Specialization>> getSpecializations();
}
