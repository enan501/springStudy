package com.backdev.happy.wblserver.app.user.service;

import com.backdev.happy.wblserver.app.user.model.UserDetailsVO;
import com.backdev.happy.wblserver.app.user.repository.UserRepository;
import com.backdev.happy.wblserver.exception.UserNotFoundException;
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
