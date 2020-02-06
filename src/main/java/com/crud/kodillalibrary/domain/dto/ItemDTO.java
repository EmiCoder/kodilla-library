package com.crud.kodillalibrary.domain.dto;

import com.crud.kodillalibrary.domain.main.Book;
import com.crud.kodillalibrary.domain.main.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {

    private int id;
    private String status;
    private Book book;
    private Item item;

}
