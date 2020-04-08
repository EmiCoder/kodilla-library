package com.crud.kodillalibrary.domain.main;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="READERS")
public class Reader {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Column(name="FIRST_NAME")
    private String firstname;
    @Column(name="LAST_NAME")
    private String lastname;
    @Column(name="ACCOUNT_CREATING_DATE")
    private String accountCreatingDate;

    @OneToMany(
            targetEntity=Loan.class,
            mappedBy="reader",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Loan> loans = new ArrayList<>();

    @OneToMany(
            targetEntity=LoanProcess.class,
            mappedBy="reader",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<LoanProcess> loanProcesses = new ArrayList<>();

}
