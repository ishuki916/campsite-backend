package com.maki.springCampsite.domain;

import com.maki.springCampsite.domain.constant.Auth;
import com.maki.springCampsite.domain.constant.UserSource;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Document
@Data
public class User implements UserDetails {

    @Id
    private String id;

    private String password;

    private String username;

    private String email;

    // source of user, e.g. google, facebook, etc.
    private UserSource source;

    private Auth auth;


    public void validate() {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username is required");
        }
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + auth.name()));
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
        return true;
    }
}
