package com.crud.kodillalibrary.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReaderDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String accountCreatingDate;
}
