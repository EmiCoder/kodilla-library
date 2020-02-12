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
