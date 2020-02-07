package com.crud.kodillalibrary.domain.main;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
    private String accountCreatingDate;

    @OneToMany(
            targetEntity=Loan.class,
            mappedBy="reader",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Loan> loans = new ArrayList<>();

}
