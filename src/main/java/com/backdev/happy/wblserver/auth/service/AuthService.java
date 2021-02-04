package com.backdev.happy.wblserver.auth.service;

import com.backdev.happy.wblserver.auth.domain.entity.Member;

import java.util.List;

public interface AuthService {

    Member signin(Member member);

    Member createUser(Member member);

    Member findUserByUserEmail(String userEmail);

    List<Member> findAll();


}