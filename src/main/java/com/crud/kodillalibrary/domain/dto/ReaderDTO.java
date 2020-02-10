package com.crud.kodillalibrary.domain.dto;

import com.crud.kodillalibrary.domain.main.Loan;
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
