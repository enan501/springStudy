package com.backdev.happy.wblserver.member.dto;

import com.backdev.happy.wblserver.global.config.security.UserRole;
import com.backdev.happy.wblserver.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class SignUpRequest {
        private String phone;
        private String name;
        private String password;
        private String role;

        @Builder
        public SignUpRequest(String phone, String name, String password, String role){
            this.phone = phone;
            this.name = name;
            this.password = password;
            this.role = role;
        }

        public Member toEntity(PasswordEncoder encoder){
            return Member.builder()
                    .pw(encoder.encode(password))
                    .phone(phone)
                    .name(name)
                    .role(UserRole.findByString(role))
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class SignInRequest {
        private String phone;
        private String password;

        @Builder
        public SignInRequest(String phone, String password){
            this.phone = phone;
            this.password = password;
        }
    }
}
