package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="LOAN_LIST")
public class Loan {

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
    public int getReaderID() {
        return reader.getId();
    }

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ITEM_ID")
    public int getItemId() {
        return item.getId();
    }
}
