package com.crud.kodillalibrary.domain.main;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NamedNativeQuery(
        name = "Item.findByTitle",
        query = "select b.TITLE, i.ITEM_STATUS, i.id from items as i, books as b where i.BOOK_ID = b.id and b.TITLE = :TITLE",
        resultClass = Item.class
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="ITEM_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;

    @OneToMany(
            targetEntity=Loan.class,
            mappedBy="item",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Loan> loans = new ArrayList<>();


}
