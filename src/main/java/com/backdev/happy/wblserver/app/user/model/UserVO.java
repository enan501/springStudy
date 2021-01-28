package com.backdev.happy.wblserver.app.user.model;


import com.backdev.happy.wblserver.app.common.model.CommonVO;
import com.backdev.happy.wblserver.enums.role.UserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
public class UserVO extends CommonVO implements Serializable {

    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String userEmail;

    @Setter
    @Column(nullable = false)
    private String userPw;

    @Setter
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public UserVO(String userEmail, String userPw){
        this.userEmail = userEmail;
        this.userPw = userPw;
    }

}
