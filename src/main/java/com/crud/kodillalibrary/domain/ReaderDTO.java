package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate AccountCreatingDate;
}
