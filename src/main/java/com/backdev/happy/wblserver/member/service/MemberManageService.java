package com.backdev.happy.wblserver.member.service;

import com.backdev.happy.wblserver.member.domain.Member;
import com.backdev.happy.wblserver.member.dto.MemberDto;

public interface MemberManageService {
    Member signUpMember(MemberDto.SignUpRequest dto);
    Member signInMember(MemberDto.SignInRequest dto);
}
