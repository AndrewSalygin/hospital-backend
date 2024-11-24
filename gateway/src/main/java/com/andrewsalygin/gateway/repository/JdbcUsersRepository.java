package com.andrewsalygin.gateway.repository;

import com.andrewsalygin.gateway.dto.admin.UserInfo;
import com.andrewsalygin.gateway.dto.security.UserCredentials;
import com.andrewsalygin.gateway.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class JdbcUsersRepository {

    private final JdbcClient client;

    private static final String EMAIL_FIELD = "email";
    private static final String ROLE_FIELD = "role";
    private static final String ID_FIELD = "userId";

    public Mono<UserCredentials> findByEmail(String email) {
        return Mono.fromSupplier(() -> client.sql("SELECT email, password, role FROM users WHERE email = :email")
            .param(EMAIL_FIELD, email)
            .query(UserCredentials.class)
            .optional()
            .orElseThrow(UserNotFoundException::new));
    }

    public Mono<Void> changeUserRights(Integer userId, String role) {
        return Mono.fromCallable(() -> {
            client.sql("UPDATE users SET role = :role WHERE userId = :userId")
                .param("role", role)
                .param("userId", userId)
                .update();
            return null; // Возвращаем null, так как возвращаем Mono<Void>
        });
    }

    public Mono<Void> deleteUser(Integer userId) {
        return Mono.fromCallable(() -> { client.sql("DELETE FROM users WHERE userId = :userId")
            .param(ID_FIELD, userId)
            .update(); return null; });
    }

    public Flux<UserInfo> getUsers(Integer limit, Integer offset) {
        String query;
        if (limit == -1) {
            query = "SELECT userId, email, password, role FROM users " +
                "ORDER BY userId OFFSET :offset ROWS";
        } else {
            query = "SELECT userId, email, password, role FROM users " +
                "ORDER BY userId OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";
        }

        return Flux.defer(() -> Flux.fromIterable(client.sql(query)
            .param("offset", offset)
            .param("limit", limit)
            .query(UserInfo.class)
            .list()));
    }
}
