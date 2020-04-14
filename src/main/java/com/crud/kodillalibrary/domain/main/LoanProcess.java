package com.crud.kodillalibrary.domain.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LOAN_PROCESS")
public class LoanProcess {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="READER_ID")
    private Reader user;

    @Column(name="BOOK_TITLE")
    private String bookTitle;

    @Column(name="LOAN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    @Column(name="RETURN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

}
