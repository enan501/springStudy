package com.backdev.happy.wblserver.bakery.domain;

import com.backdev.happy.wblserver.global.config.security.UserRole;
import com.backdev.happy.wblserver.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString(of = {"member", "name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bakeries")
@Entity
public class Bakery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne //(cascade={CascadeType.ALL})
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member; // 외래키가 있는 곳이 연관관계 주인 -> 하나의 member는 여러 개의 bakery를 가질 수 있으므로(one to many 관계) 주인을 seller로 설정

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 255)
    private String img;

    @Column(nullable = false, length = 45)
    private String address;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date  updateTime;

    @Builder
    public Bakery(final Long memberId, final String name, final String img, final String address, final String introduction){
        this.member = new Member(memberId);
        this.name = name;
        this.img = img;
        this.address = address;
        this.introduction = introduction;
    }
}
