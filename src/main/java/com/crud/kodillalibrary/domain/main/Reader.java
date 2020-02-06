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
@Table(name="READERS")
public class Reader {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="FIRST_NAME")
    private String firstname;
    @Column(name="LAST_NAME")
    private String lastname;
    @Column(name="ACCOUNT_CREATING_DATE")
    private LocalDate accountCreatingDate;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="LOAN_ID")
    private Loan loan;

}
