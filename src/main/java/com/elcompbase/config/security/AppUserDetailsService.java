package com.elcompbase.config.security;

import com.elcompbase.model.entity.User;
import com.elcompbase.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public AppUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        return AppUserDetails.toAppUserDetails(user);
    }

    public AppUserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        User user = userService.getById(id);
        return AppUserDetails.toAppUserDetails(user);
    }
}
