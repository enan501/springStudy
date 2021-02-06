package com.backdev.happy.wblserver.auth.domain.repository;

import com.backdev.happy.wblserver.auth.domain.entity.Role;
import com.backdev.happy.wblserver.auth.domain.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
