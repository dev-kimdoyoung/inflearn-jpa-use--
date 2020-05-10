package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 클래스 상속을 위한 전략 수립 (SINGLE TABLE 외 2개 전략 존재)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 중복 내용 구분을 위한 컬럼 명 설정
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //    비즈니스 로직

    /**
     *  stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     *  stock 감소
     */
    public void removeStock(int quantity) {
        int resStock = this.stockQuantity - quantity;
        if(resStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = resStock;
    }
}



