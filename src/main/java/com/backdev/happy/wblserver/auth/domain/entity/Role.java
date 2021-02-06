package com.backdev.happy.wblserver.auth.domain.entity;

import com.backdev.happy.wblserver.auth.domain.entity.UserRole;
import com.backdev.happy.wblserver.global.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Table(name="roles")
public class Role extends BaseEntity {
    String name;

    @OneToMany(mappedBy = "role")
    Collection<UserRole> members;

    @Builder
    public Role(String name){
        this.name = name;
    }
}
