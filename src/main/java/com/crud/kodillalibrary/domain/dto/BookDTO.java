package com.crud.kodillalibrary.domain.dto;

import com.crud.kodillalibrary.domain.main.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private String published;
    private List<Item> items = new ArrayList<>();
}
