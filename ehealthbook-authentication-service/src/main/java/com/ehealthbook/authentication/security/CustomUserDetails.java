package com.ehealthbook.authentication.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final UUID id;

    private final String role;

    public CustomUserDetails(UUID id, String username, String password, String role, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.role = role;
    }
}
