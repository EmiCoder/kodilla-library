package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private LocalDate published;
}
