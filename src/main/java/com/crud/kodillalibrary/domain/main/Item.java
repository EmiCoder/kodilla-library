package com.crud.kodillalibrary.domain.main;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ITEMS")
public class Item {

    private Book book;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="BOOK_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    public Book getBook() {
        return book;
    }

    @OneToMany(
            targetEntity=Loan.class,
            mappedBy="item",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Loan> loans = new ArrayList<>();



}
