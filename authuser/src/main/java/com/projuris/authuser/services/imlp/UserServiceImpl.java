package com.projuris.authuser.services.imlp;

import com.projuris.authuser.models.UserModel;
import com.projuris.authuser.repositories.UserRepository;
import com.projuris.authuser.services.UserService;
import com.projuris.authuser.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public Page<UserModel> findAll(SpecificationTemplate.UserSpec spec, Pageable pageable) {
        return userRepository.findAll(spec,pageable);
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {

        return userRepository.findById(userId);
    }

    @Override
    public boolean existsByUsername(String userName) {

        return userRepository.existsByUsername(userName);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(UserModel userModel) {

        userRepository.save(userModel);
    }
}
