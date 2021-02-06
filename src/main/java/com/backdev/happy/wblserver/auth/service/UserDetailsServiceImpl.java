package com.backdev.happy.wblserver.auth.service;

import com.backdev.happy.wblserver.auth.domain.repository.UserRepository;
import com.backdev.happy.wblserver.auth.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("name", name));
    }
    
}