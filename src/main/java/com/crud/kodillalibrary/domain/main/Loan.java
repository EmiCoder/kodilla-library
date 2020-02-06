package com.crud.kodillalibrary.domain.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
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
    private Reader reader;
    private Item item;

    public Loan(LocalDate loanDate, LocalDate returnDate) {
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="READER_ID")
    public Reader getReader() {
        return reader;
    }

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ITEM_ID")
    public Item getItem() {
        return item;
    }
}
