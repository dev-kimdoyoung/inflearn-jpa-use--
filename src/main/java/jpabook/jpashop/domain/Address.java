package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable     // 내장 타입 임을 명시
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // 기본 생성자는 접근 제한자를 protected로 두어 사용자가 접근할 수 없도록 한다.
    // protected로 두면 스펙 상 사용하면 안된다는 것을 암묵적으로 공유
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
