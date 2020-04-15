package com.crud.kodillalibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LoanDTO {
    private Integer id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Integer readerId;
    private Integer itemId;
}
