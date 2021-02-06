package com.backdev.happy.wblserver.auth.domain.entity;

import com.backdev.happy.wblserver.global.domain.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name = "user_roles")
public class UserRole extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    Member member;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @Builder
    public UserRole(Member member, Role role){
        this.member = member;
        this.role = role;
    }
}