package com.andrewsalygin.gateway.security;

import com.andrewsalygin.gateway.dto.security.UserCredentials;
import com.andrewsalygin.gateway.repository.JdbcUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JdbcUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserCredentials authUserInfo = usersRepository.findByEmail(username).block();

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        if (authUserInfo.isAdmin()) {
            roles.add("ADMIN");
        }

        return User
            .withUsername(authUserInfo.email())
            .password(authUserInfo.password())
            .roles(roles.toArray(new String[0]))
            .build();
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // SHOW_ACCOUNT, WITHDRAW_MONEY, SEND_MONEY
//        // ROLE_ADMIN, ROLE_USER - это роли
//        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
//    }

}
