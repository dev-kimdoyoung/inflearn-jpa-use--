package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *      도메인 모델 패턴 형식으로 사용
 */

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // cascade는 order과 연관 관계를 가지는 내용에 persist를 전파하여 코드량을 줄여준다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // Java 8부터는 Annotation 없어도 해당 데이터 타입을 Hibernate에서 자동 지원
    private LocalDateTime orderDate;    // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;     // 주분 상태 [ORDER, CANCEL]

    //===============연관 관계 메서드===============//
   public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
   }

   public void addOrderItems(OrderItem orderItem) {
       orderItems.add(orderItem);
       orderItem.setOrder(this);
   }

   public void setDelivery(Delivery delivery) {
       this.delivery = delivery;
       delivery.setOrder(this);
   }

   // 생성 메소드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
       Order order = new Order();
       order.setMember(member);
       order.setDelivery(delivery);

       for(OrderItem orderItem : orderItems) {
           order.addOrderItems(orderItem);
       }

       order.setStatus(OrderStatus.ORDER);
       order.setOrderDate(LocalDateTime.now());

       return order;
    }

    // 비즈니스 로직
    /**
     *  주문 취소
     */
    public void cancel() {
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);

        for(OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    // 조회 로직
    /**
     *  전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;

        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

}





