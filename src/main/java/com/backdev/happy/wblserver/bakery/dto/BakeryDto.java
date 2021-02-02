package com.backdev.happy.wblserver.bakery.dto;

import com.backdev.happy.wblserver.bakery.domain.Bakery;
import com.backdev.happy.wblserver.global.config.security.UserRole;
import com.backdev.happy.wblserver.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BakeryDto {

    @Getter
    @NoArgsConstructor
    public static class CreateRequest {
        private Long memberId;
        private String name;
        private String img;
        private String address;
        private String introduction;

        @Builder
        public CreateRequest(Long memberId, String name, String img, String address, String introduction){
            this.memberId = memberId;
            this.name = name;
            this.img = img;
            this.address = address;
            this.introduction = introduction;
        }

        public Bakery toEntity(){
            return Bakery.builder()
                    .memberId(memberId)
                    .name(name)
                    .img(img)
                    .address(address)
                    .introduction(introduction)
                    .build();
        }
    }
}
