package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded       // 내장 타입을 데이터 타입으로 가짐을 명시
    private Address address;

    @OneToMany(mappedBy = "member")          // mappedby 매개 변수로 자신은 연관 관계의 주인(외래키 소유자)가 아님을 명시
    private List<Order> orders = new ArrayList<>();
}
