package com.projuris.authuser.repositories;

import com.projuris.authuser.enums.RoleType;
import com.projuris.authuser.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    Optional<RoleModel> findByRoleName(String name);
}
