package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="ITEMS")
public class Item {

    private Book book;

    public Item(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="BOOK_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    public int getBookID() {
        return book.getId();
    }



}
