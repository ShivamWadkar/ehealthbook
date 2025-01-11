package com.ehealthbook.authentication.service;

import com.ehealthbook.authentication.entity.User;
import com.ehealthbook.authentication.repository.UserRepository;
import com.ehealthbook.authentication.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Create authorities based on roles
        Collection<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));

        // Return CustomUserDetails with UUID
        return new CustomUserDetails(
                user.getId(),                  // UUID from the database
                user.getUsername(),
                user.getPassword(),
                user.getRole().getName(),
                authorities
        );
    }
}
