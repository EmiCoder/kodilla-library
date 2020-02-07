package com.crud.kodillalibrary.domain.main;

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
    private int id;
    @Column(name="LOAN_DATE")
    private LocalDate loanDate;
    @Column(name="RETURN_DATE")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name="READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;


}
