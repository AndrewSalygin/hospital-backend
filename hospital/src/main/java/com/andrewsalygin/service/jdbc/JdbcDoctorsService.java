package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.dto.surgeries.SurgeryShortInfoDTO;
import com.andrewsalygin.hospital.model.DoctorFullInfo;
import com.andrewsalygin.hospital.model.DoctorInfo;
import com.andrewsalygin.hospital.model.DoctorShortInfo;
import com.andrewsalygin.hospital.model.DoctorSpecialization;
import com.andrewsalygin.hospital.model.SurgeryShortInfo;
import com.andrewsalygin.repository.interfaces.DoctorsRepository;
import com.andrewsalygin.service.interfaces.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcDoctorsService implements DoctorsService {

    private final DoctorsRepository doctorsRepository;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<SurgeryShortInfo>> getSurgeriesForDoctor(
        Integer doctorId,
        Integer limit,
        Integer offset
    ) {
        List<SurgeryShortInfoDTO> resultFromRepository =
            doctorsRepository.getSurgeriesForDoctor(doctorId, limit, offset);

        Type listType = new TypeToken<List<SurgeryShortInfo>>() {
        }.getType();
        List<SurgeryShortInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DoctorInfo> getFirstAvailableDoctorBySpecializationAndExperience(String specializationName) {
        DoctorInfoDTO doctorInfoDTO =
            doctorsRepository.getFirstAvailableDoctorBySpecializationAndExperience(specializationName);

        DoctorInfo doctorInfo = modelMapper.map(doctorInfoDTO, DoctorInfo.class);
        return new ResponseEntity<>(doctorInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctors(Integer limit, Integer offset) {
        List<DoctorShortInfoDTO> resultFromRepository = doctorsRepository.getDoctors(limit, offset);

        Type listType = new TypeToken<List<DoctorShortInfo>>() {
        }.getType();
        List<DoctorShortInfo> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DoctorFullInfo> getDoctor(Integer doctorId) {
        DoctorFullInfoDTO doctorFullInfoDTO = doctorsRepository.getDoctor(doctorId);

        DoctorFullInfo doctorFullInfo = modelMapper.map(doctorFullInfoDTO, DoctorFullInfo.class);
        return new ResponseEntity<>(doctorFullInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DoctorSpecialization>> getDoctorSpecializations(Integer doctorId) {
        List<DoctorSpecializationDTO> resultFromRepository = doctorsRepository.getDoctorSpecializations(doctorId);

        Type listType = new TypeToken<List<DoctorSpecialization>>() {
        }.getType();
        List<DoctorSpecialization> result = modelMapper.map(resultFromRepository, listType);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDoctor(Integer doctorId) {
        doctorsRepository.deleteDoctor(doctorId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addSpecializationToDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        doctorsRepository.addSpecializationToDoctor(doctorId, specializationId, yearsOfExperience);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> changeSpecializationExperienceDoctor(
        Integer doctorId,
        Integer specializationId,
        Integer yearsOfExperience
    ) {
        doctorsRepository.changeSpecializationExperienceDoctor(doctorId, specializationId, yearsOfExperience);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
