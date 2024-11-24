package com.andrewsalygin.gateway.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoForAdminPanel {

    private Integer userId;

    private String email;

    private String role;
}
