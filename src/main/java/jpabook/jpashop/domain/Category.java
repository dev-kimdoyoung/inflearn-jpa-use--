package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
               joinColumns = @JoinColumn(name = "category_id"),
              inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)      // 모든 연관 관계는 지연 로딩으로 설정하자! (N + 1 문제 발생 방지)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY) // 모든 연관 관계는 지연 로딩으로 설정하자! (N + 1 문제 발생 방지)
    private List<Category> child = new ArrayList<>();


    public void setChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
