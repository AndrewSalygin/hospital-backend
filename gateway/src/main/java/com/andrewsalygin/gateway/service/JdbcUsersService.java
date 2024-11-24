package com.andrewsalygin.gateway.service;

import com.andrewsalygin.gateway.dto.auth.UserInfoForAdminPanel;
import com.andrewsalygin.gateway.repository.JdbcUsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcUsersService {

    private final JdbcUsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Mono<Void> changeUserRights(Integer userId, String role) {
        return usersRepository.changeUserRights(userId, role);
    }

    @Transactional
    public Mono<Void> deleteUser(Integer userId) {
        return usersRepository.deleteUser(userId);
    }

    @Transactional
    public Flux<UserInfoForAdminPanel> getUsers(Integer limit, Integer offset) {
        return usersRepository.getUsers(limit, offset)
            .map(user -> modelMapper.map(user, UserInfoForAdminPanel.class));
    }
}
