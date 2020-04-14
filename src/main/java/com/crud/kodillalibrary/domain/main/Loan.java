package com.crud.kodillalibrary.domain.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="LOANS")
public class Loan {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name="LOAN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;
    @Column(name="RETURN_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name="READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

}
