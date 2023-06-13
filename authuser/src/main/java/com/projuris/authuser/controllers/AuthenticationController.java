package com.projuris.authuser.controllers;

import com.projuris.authuser.dtos.UserDto;
import com.projuris.authuser.enums.RoleType;
import com.projuris.authuser.models.RoleModel;
import com.projuris.authuser.models.UserModel;
import com.projuris.authuser.services.RoleService;
import com.projuris.authuser.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody  @Validated(UserDto.UserView.RegistrationPost.class)
                                                   UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            log.warn("Username {} is Already Taken ", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is Already Taken!");
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} is Already Taken ", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already Taken!");
        }
        RoleModel roleModel = roleService.findByRoleName(userDto.getRoleModels())
                .orElseThrow(() -> new RuntimeException("Error Role is not found."));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userService.save(userModel);
        log.info("User saved successfully userId {} ", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}