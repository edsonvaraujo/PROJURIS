package com.projuris.authuser.services;

import com.projuris.authuser.enums.RoleType;
import com.projuris.authuser.models.RoleModel;

import java.util.Optional;

public interface RoleService {

    Optional<RoleModel> findByRoleName(String roleType);
}
