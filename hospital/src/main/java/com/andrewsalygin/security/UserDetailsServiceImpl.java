package com.andrewsalygin.security;

import com.andrewsalygin.dto.security.UserCredentials;
import com.andrewsalygin.repository.interfaces.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserCredentials authUserInfo = usersRepository.findByEmail(username);

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
