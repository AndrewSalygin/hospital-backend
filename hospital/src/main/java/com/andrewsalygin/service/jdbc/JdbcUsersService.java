package com.andrewsalygin.service.jdbc;

import com.andrewsalygin.repository.UsersRepository;
import com.greencats.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcUsersService implements UsersService {

    private final UsersRepository usersRepository;


}
