package com.backdev.happy.wblserver.auth.service;

import com.backdev.happy.wblserver.auth.domain.repository.UserRepository;
import com.backdev.happy.wblserver.auth.domain.entity.Member;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("userService")
public class AuthServiceImpl implements AuthService {

    @NonNull
    private UserRepository userRepository;

    @Override
    public Member signin(Member member) {
        return null;
    }

    @Override
    public Member createUser(Member member) {
        return userRepository.save(member);
    }

    @Override
    public Member findUserByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).get();
    }

}