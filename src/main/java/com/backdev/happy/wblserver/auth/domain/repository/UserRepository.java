package com.backdev.happy.wblserver.auth.domain.repository;

import com.backdev.happy.wblserver.auth.domain.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<Member, Long> {

    Member findByUserEmailAndUserPw(String userId, String userPw);

    Optional<Member> findByUserEmail(String userEmail);
}