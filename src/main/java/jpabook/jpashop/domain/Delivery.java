package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    // delivery 클래스에 의해 매핑되었고 연관 관계 주인이 아님을 명시
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // Enumeration 타입 생성
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;      // READY, COMP
}
