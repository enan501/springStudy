package com.backdev.happy.wblserver.member.domain;

import com.backdev.happy.wblserver.global.config.security.UserRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@ToString(of = {"id", "phone", "name"})
@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA에서는 프록시 객체가 필요함 -> 기본 생성자 하나가 반드시 있어야 함
@Table(name = "members")
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String pw;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false, length = 11, unique = true)
    private String phone;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date  updateTime;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role; //회원은 여러 개의 권한을 가질 수 있어서 collection 이 옳지만 일만 단일 권한만

    @Builder
    public Member(String pw, String name, String phone, UserRole role){
        this.pw = pw;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public Member(Long id){
        this.id = id;
    }

    public void updateProfile(final String name, final String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getValue()));
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() { // unique하게 회원 구분
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() { //계정이 만료가 안되었는지
        return true; // 사용안해서 true
    }

    @Override
    public boolean isAccountNonLocked() { //계정이 잠기지 않았는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //계정 패스워드가 만료되진 않았는지
        return true;
    }

    @Override
    public boolean isEnabled() { //계정이 사용가능한지
        return true;
    }
}
