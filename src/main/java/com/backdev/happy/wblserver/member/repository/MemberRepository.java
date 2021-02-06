package com.backdev.happy.wblserver.member.repository;

import com.backdev.happy.wblserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByPhone(String phone);
}
