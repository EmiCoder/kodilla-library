package com.crud.kodillalibrary.domain.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="TITLE")
    private String title;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="PUBLISHED")
    private String published;

    @OneToMany(
            targetEntity=Item.class,
            mappedBy="book",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    @JoinColumn(name="AMOUNT_OF_BOOK")
    private List<Item> items = new ArrayList<>();



}
