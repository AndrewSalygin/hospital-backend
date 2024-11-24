package com.andrewsalygin.applicationservice.service.jdbc;

import com.andrewsalygin.applicationservice.client.SpecializationClient;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorAddRequestDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorFullInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorShortInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecialInfoDTO;
import com.andrewsalygin.applicationservice.dto.doctor.DoctorSpecializationDTO;
import com.andrewsalygin.applicationservice.dto.surgeries.SurgeryShortInfoDTO;
import com.andrewsalygin.applicationservice.model.DiseaseFullInfo;
import com.andrewsalygin.applicationservice.model.DoctorAddRequest;
import com.andrewsalygin.applicationservice.model.DoctorFullInfo;
import com.andrewsalygin.applicationservice.model.DoctorInfo;
import com.andrewsalygin.applicationservice.model.DoctorShortInfo;
import com.andrewsalygin.applicationservice.model.DoctorSpecialization;
import com.andrewsalygin.applicationservice.model.IdResponse;
import com.andrewsalygin.applicationservice.model.Specialization;
import com.andrewsalygin.applicationservice.model.SurgeryShortInfo;
import com.andrewsalygin.applicationservice.repository.interfaces.DoctorsRepository;
import com.andrewsalygin.applicationservice.service.interfaces.DoctorsService;
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

    private final SpecializationClient specializationClient;

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
        List<Specialization> specializations = specializationClient.getSpecializations().getBody();
        List<DoctorSpecialInfoDTO> resultFromRepository = doctorsRepository.getDoctors(limit, offset);

        for (var doctorElement : resultFromRepository) {
            doctorElement.setSpecializationName(
                specializations.stream().filter(elem -> elem.getSpecializationId()
                .equals(doctorElement.getSpecializationId()))
                .findFirst()
                .get()
                .getSpecializationName()
            );
        }

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
        List<DoctorSpecializationDTO> resultFromRepository = specializationClient.getDoctorSpecializations(doctorId).getBody();

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

    @Override
    public ResponseEntity<IdResponse> addDoctor(DoctorAddRequest doctorAddRequest) {
        DoctorAddRequestDTO doctorAddRequestDTO = modelMapper.map(doctorAddRequest, DoctorAddRequestDTO.class);
        Integer doctorId = doctorsRepository.addDoctor(doctorAddRequestDTO);
        IdResponse idResponse = new IdResponse();
        idResponse.setId(doctorId);
        return new ResponseEntity<>(idResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateDoctor(Integer doctorId, DoctorAddRequest doctorAddRequest) {
        DoctorAddRequestDTO doctorAddRequestDTO = modelMapper.map(doctorAddRequest, DoctorAddRequestDTO.class);
        doctorsRepository.updateDoctor(doctorId, doctorAddRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> restoreDoctor(Integer doctorId) {
        doctorsRepository.restoreDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DoctorShortInfo>> getDoctorsForSpecialization(Integer specializationId) {
        return new ResponseEntity<>(doctorsRepository.getDoctorsForSpecialization(specializationId), HttpStatus.OK);
    }
}
