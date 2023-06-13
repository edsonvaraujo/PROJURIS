package com.projuris.authuser.services.imlp;

import com.projuris.authuser.enums.RoleType;
import com.projuris.authuser.models.RoleModel;
import com.projuris.authuser.repositories.RoleRepository;
import com.projuris.authuser.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleModel> findByRoleName(String roleType) {
        return roleRepository.findByRoleName(roleType);
    }
}
