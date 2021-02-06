package com.backdev.happy.wblserver.member.service;

import com.backdev.happy.wblserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String userPk) {
        return memberRepository.findById(Long.valueOf(userPk)).get();
    }
}