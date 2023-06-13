package com.projuris.authuser.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.projuris.authuser.dtos.UserDto;
import com.projuris.authuser.models.UserModel;
import com.projuris.authuser.services.UserService;
import com.projuris.authuser.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(SpecificationTemplate.UserSpec spec,
                                                       @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
                                                       @RequestParam(required = false) UUID soId){
        Page<UserModel> userModelPage = userService.findAll(spec, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUsers(@PathVariable(value = "userId") UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "userId")UUID userId,
                                             @RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                             UserDto userDto) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        } else {
            var userModel = userModelOptional.get();
            BeanUtils.copyProperties(userDto, userModel);
            userService.save(userModel);
            log.info("User updated successfully userId {} ", userModel.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }
        @PutMapping("/{userId}/password")
        public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId")UUID userId,
                                                    @RequestBody  @Validated(UserDto.UserView.PasswordPut.class)
                                                    UserDto userDto){
            log.debug("PUT updatePassword userDto received {} ", userDto.toString());
            Optional<UserModel> userModelOptional = userService.findById(userId);
            if (!userModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
            }
            if (userModelOptional.get().getPassword().equals(userDto.getPassword())) {
                log.warn("Mismatched old password userId {} ", userId);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
            }else {
                var userModel = userModelOptional.get();
                userModel.setPassword(userDto.getPassword());
                userService.save(userModel);
                log.info("Password updated successfully userId {} ", userModel.getUserId());
                return ResponseEntity.status(HttpStatus.OK).body("Password update successfully.");
            }
        }
}
