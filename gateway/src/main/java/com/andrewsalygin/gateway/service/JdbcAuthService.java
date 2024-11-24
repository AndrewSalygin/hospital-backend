package com.andrewsalygin.gateway.service;

import com.andrewsalygin.gateway.dto.auth.AuthorizeUserRequestDTO;
import com.andrewsalygin.gateway.dto.auth.JWTToken;
import com.andrewsalygin.gateway.dto.security.User;
import com.andrewsalygin.gateway.exception.UserBadCredentialException;
import com.andrewsalygin.gateway.repository.JdbcAuthRepository;
import com.andrewsalygin.gateway.util.JWTUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class JdbcAuthService {

    private final JdbcAuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTUtil jwtUtil;

    public Mono<JWTToken> performLogin(String email, String password) {
        return Mono.fromCallable(() -> {
            String encodedPasswordFromDB = authRepository.getEncodedPasswordByEmail(email);

            if (passwordEncoder.matches(password, encodedPasswordFromDB)) {
                Integer userId = authRepository.getUserIdByEmail(email);
                User user = authRepository.getUserById(userId);
                String token = jwtUtil.generateToken(userId, email, user.role());
                JWTToken jwtToken = new JWTToken();
                jwtToken.setJwtToken(token);
                return jwtToken;
            } else {
                throw new UserBadCredentialException();
            }
        });
    }

    public Mono<JWTToken> performRegistration(String email, String password) {
        return Mono.fromCallable(() -> {
            String encodedPassword = passwordEncoder.encode(password);
            AuthorizeUserRequestDTO authorizeUserRequestDTO = new AuthorizeUserRequestDTO(email, encodedPassword);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<AuthorizeUserRequestDTO>> violations = validator.validate(authorizeUserRequestDTO);

            if (violations.isEmpty()) {
                Integer userId = authRepository.performRegistration(authorizeUserRequestDTO);
                User user = authRepository.getUserById(userId);
                String token = jwtUtil.generateToken(userId, email, user.role());
                JWTToken jwtToken = new JWTToken();
                jwtToken.setJwtToken(token);
                return jwtToken;
            } else {
                throw new UserBadCredentialException();
            }
        });
    }
}
