package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.dto.admin.UserInfo;
import com.andrewsalygin.hospital.model.UserInfoForAdminPanel;
import com.andrewsalygin.repository.interfaces.UsersRepository;
import com.andrewsalygin.service.interfaces.UsersService;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcUsersService implements UsersService {

    private final UsersRepository usersRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<Void> changeUserRights(Integer userId, String role) {
        usersRepository.changeUserRights(userId, role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(Integer userId) {
        usersRepository.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<List<UserInfoForAdminPanel>> getUsers(Integer limit, Integer offset) {
        List<UserInfo> users = usersRepository.getUsers(limit, offset);
        Type listType = new TypeToken<List<UserInfoForAdminPanel>>() {}.getType();
        List<UserInfoForAdminPanel> response = modelMapper.map(users, listType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
