package com.crud.kodillalibrary.domain.main;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="LOAN_ID")
    private Loan loan;



}
