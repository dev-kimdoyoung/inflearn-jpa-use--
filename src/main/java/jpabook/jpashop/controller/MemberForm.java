package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "이름을 입력하세요!!")
    private String name;

    private String city;

    private String street;

    private String zipcode;

}
