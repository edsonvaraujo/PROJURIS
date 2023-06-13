package com.projuris.authuser.specifications;

import com.projuris.authuser.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;

public class SpecificationTemplate {

    @And({
            @Spec(path = "fullName", spec = Like.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "userTypes", spec = Equal.class)})

    public interface UserSpec extends Specification<UserModel> {}
}
