package com.backdev.happy.wblserver.auth.service;

import com.backdev.happy.wblserver.auth.domain.repository.UserRepository;
import com.backdev.happy.wblserver.global.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetailsVO loadUserByUsername(String userEmail) {
        return userRepository.findByUserEmail(userEmail).map(u -> new UserDetailsVO(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue())))).orElseThrow(() -> new UserNotFoundException(userEmail));
    }
    
}