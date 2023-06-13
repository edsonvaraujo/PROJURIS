package com.projuris.authuser.services;

import com.projuris.authuser.models.UserModel;
import com.projuris.authuser.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Page<UserModel> findAll(SpecificationTemplate.UserSpec spec, Pageable pageable);

    Optional<UserModel> findById(UUID userId);

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    void save(UserModel userModel);
}
