package com.crud.kodillalibrary.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private String published;
}
