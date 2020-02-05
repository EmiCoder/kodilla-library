package com.crud.kodillalibrary.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="READERS")
public class Reader {


    public Reader(String firstname, String lastname, LocalDate accountCreatingDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreatingDate = accountCreatingDate;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name="FIRST_NAME")
    private String firstname;
    @Column(name="LAST_NAME")
    private String lastname;
    @Column(name="ACCOUNT_CREATING_DATE")
    private LocalDate accountCreatingDate;

}
