package com.backdev.happy.wblserver.auth.domain.repository;

import com.backdev.happy.wblserver.auth.domain.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByName(String name);
    Optional<Member> findByNameAndPassword(String name, String password);
    Boolean existsByName(String name);
    Boolean existsByPhone(String phone);
}