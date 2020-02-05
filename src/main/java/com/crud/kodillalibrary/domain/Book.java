package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="BOOKS")
public class Book {

    public Book(String title, String author, LocalDate published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="TITLE")
    private String title;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="PUBLISHED")
    private LocalDate published;

    @OneToMany(
            targetEntity=Item.class,
            mappedBy="book",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Item> items = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Loan loan;

}
