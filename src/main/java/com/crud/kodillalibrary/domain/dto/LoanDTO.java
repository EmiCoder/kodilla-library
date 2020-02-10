package com.crud.kodillalibrary.domain.dto;


import com.crud.kodillalibrary.domain.main.Item;
import com.crud.kodillalibrary.domain.main.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanDTO {
    private Integer id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Reader reader;
    private Item item;
}
