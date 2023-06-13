package com.projuris.serviceordem.controllers;

import com.projuris.serviceordem.clients.UserCourseClient;
import com.projuris.serviceordem.dtos.UserDto;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class UserServiceOrderController {

    @Autowired
    UserCourseClient userCourseClient;

    @GetMapping("/so/users/so")
    public ResponseEntity<Page<UserDto>> getAllUser(SpecificationTemplate.SoSpec spec,
                                                  @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userCourseClient.getAllUser(spec, pageable));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getOneUserById(@PathVariable(value = "userId") UUID userId){
       var userDto = userCourseClient.getOneUserById(userId).getBody();
       log.info("{}", userDto.getFullName());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}


