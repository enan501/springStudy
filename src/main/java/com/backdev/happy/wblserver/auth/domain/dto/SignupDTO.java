package com.backdev.happy.wblserver.auth.domain.dto;

import com.backdev.happy.wblserver.auth.domain.entity.Member;
import com.backdev.happy.wblserver.auth.domain.entity.UserRole;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.Collection;

@Getter
@Valid
public class SignupDTO {
    String name;
    String phone;
    String password;

    public Member toEntity(PasswordEncoder encoder){
        return Member.builder()
                .password(encoder.encode(password))
                .phone(phone)
                .name(name)
                .build();
    }
}
