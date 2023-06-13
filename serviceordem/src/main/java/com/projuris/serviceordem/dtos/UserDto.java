package com.projuris.serviceordem.dtos;

import com.projuris.serviceordem.enums.UserStatus;
import com.projuris.serviceordem.enums.UserTypes;
import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String email;
    private String password;
    private String fullName;
    private UserStatus userStatus;
    private UserTypes userTypes;

}
