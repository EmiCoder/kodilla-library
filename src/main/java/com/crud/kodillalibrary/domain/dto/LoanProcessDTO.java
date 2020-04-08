package com.crud.kodillalibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanProcessDTO {

    private Integer id;
    private Integer readerId;
    private String bookTitle;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
