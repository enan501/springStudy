package com.backdev.happy.wblserver.member.service.impl;

import com.backdev.happy.wblserver.member.domain.Member;
import com.backdev.happy.wblserver.member.dto.MemberDto;
import com.backdev.happy.wblserver.member.repository.MemberRepository;
import com.backdev.happy.wblserver.member.service.MemberManageService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberManageServiceImpl implements MemberManageService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public Member signUpMember(MemberDto.SignUpRequest dto) {
        return memberRepository.save(dto.toEntity(passwordEncoder));
    }

    @Override
    public Member signInMember(MemberDto.SignInRequest dto) {
        Member member = memberRepository.findByPhone(dto.getPhone()).orElseThrow(() -> new RuntimeException("fail to sign in"));
        if (!passwordEncoder.matches(dto.getPassword(), dto.getPassword())) {
            throw new RuntimeException("different password");
        }
        return member;
    }
}
