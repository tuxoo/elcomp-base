package com.elcompbase.config.security;

import com.elcompbase.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class AppUserDetails implements UserDetails {

    private UUID id;
    private String login;
    private Boolean isEnabled;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static AppUserDetails toAppUserDetails(User user) {
        AppUserDetails userDetails = new AppUserDetails();
        userDetails.id = user.id();
        userDetails.login = user.loginEmail();
        userDetails.isEnabled = user.isEnabled();
        userDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.role().name()));
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
