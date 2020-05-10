package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Pagination 실험을 위한 Class
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private double price;
}
