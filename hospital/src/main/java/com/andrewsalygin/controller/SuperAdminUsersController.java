package com.andrewsalygin.controller;

import com.andrewsalygin.hospital.api.AdminApi;
import com.andrewsalygin.hospital.api.SuperApi;
import com.andrewsalygin.hospital.model.IdResponse;
import com.andrewsalygin.hospital.model.Specialization;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminUsersController implements SuperApi {
    
}
