package com.crud.kodillalibrary.domain.dto;

import com.crud.kodillalibrary.domain.main.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDTO {
    private int id;
    private String firstname;
    private String lastname;
    private LocalDate accountCreatingDate;
    private Loan loan;
}
