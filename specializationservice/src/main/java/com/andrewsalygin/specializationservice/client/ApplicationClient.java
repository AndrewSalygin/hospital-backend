package com.andrewsalygin.specializationservice.client;

import com.andrewsalygin.specializationservice.model.DoctorShortInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@FeignClient(name = "application-service", url = "${application.application-service.url}")
public interface ApplicationClient {

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/specializations/{specializationId}/doctors/",
        produces = { "application/json" }
    )
    ResponseEntity<List<DoctorShortInfo>> getSpecializationsDoctors(@PathVariable("specializationId") Integer specializationId);
}
