package com.andrewsalygin.gateway.controller;

import com.andrewsalygin.gateway.dto.auth.UserInfoForAdminPanel;
import com.andrewsalygin.gateway.service.JdbcUsersService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class SuperAdminUsersController {

    private final JdbcUsersService usersService;

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/super-admin-users",
        produces = { "application/json" }
    )
    public Flux<UserInfoForAdminPanel> getUsers( @Min(-1) @Parameter(name = "limit", description = "Количество возвращаемых пользователей", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit,
        @Min(0) @Parameter(name = "offset", description = "Смещение по количеству пользователей", in = ParameterIn.QUERY) @Valid @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return usersService.getUsers(limit, offset);
    }

    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/super-admin-users/{userId}",
        produces = { "application/json" }
    )
    public Mono<Void> deleteUser(@Parameter(name = "userId", description = "Id пользователя, который удаляется", required = true, in = ParameterIn.PATH) @PathVariable("userId") Integer userId) {
        return usersService.deleteUser(userId);
    }

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/super-admin-users/userRights/{userId}",
        produces = { "application/json" }
    )
    public Mono<Void> changeUserRights(@Parameter(name = "userId", description = "Id пользователя", required = true, in = ParameterIn.PATH) @PathVariable("userId") Integer userId,
        @NotNull
        @Parameter(name = "role", description = "Роль пользователя", required = true, in = ParameterIn.QUERY) @Valid
        @RequestParam(value = "role", required = true) String role) {
        return usersService.changeUserRights(userId, role);
    }
}
