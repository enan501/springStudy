package com.backdev.happy.wblserver.auth.domain.entity;

import com.backdev.happy.wblserver.auth.domain.entity.UserRole;
import com.backdev.happy.wblserver.global.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
public class Member extends BaseEntity implements Serializable, UserDetails {

    @Column(nullable = false, unique = true, length = 15)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    Collection<UserRole> roles;

    @Builder
    public Member(String name, String phone, String password){
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.role.name)).collect(Collectors.toSet());
    }
    @Override
    public String getUsername() {
        return name;
    }
    @Override
    public boolean isAccountNonExpired() {
        return !super.getIsDeleted();
    }
    @Override
    public boolean isAccountNonLocked() {
        return !super.getIsDeleted();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return !super.getIsDeleted();
    }

    @Override
    public boolean isEnabled() {
        return !super.getIsDeleted();
    }
}