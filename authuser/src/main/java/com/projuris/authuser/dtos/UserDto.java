package com.projuris.authuser.dtos;

import com.projuris.authuser.enums.RoleType;
import com.projuris.authuser.enums.UserStatus;
import com.projuris.authuser.models.RoleModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class UserDto {

    public interface UserView{
        public static interface RegistrationPost{}
        public static interface PasswordPut{}
    }

    private UUID userId;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Size(min = 6, max = 20, groups = UserView.RegistrationPost.class)
    private String username;
    @NotBlank(groups = UserView.RegistrationPost.class)
    @Email(groups = UserView.RegistrationPost.class)
    private String email;
    @NotBlank(groups = {UserView.RegistrationPost.class,UserView.PasswordPut.class })
    @Size(min = 6, max = 8, groups = {UserView.RegistrationPost.class,UserView.PasswordPut.class})
    private String password;
    @NotBlank(groups = UserView.RegistrationPost.class)
    @Size(min = 10, max = 255)
    private String fullName;
    @NotNull(groups = UserView.RegistrationPost.class)
    private UserStatus userStatus;
    @NotBlank(groups = UserView.RegistrationPost.class)
    private String roleModels;


}
