package com.backdev.happy.wblserver.auth.domain.repository;

import com.backdev.happy.wblserver.auth.domain.entity.Member;
import com.backdev.happy.wblserver.auth.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
