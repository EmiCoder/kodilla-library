package com.crud.kodillalibrary.domain.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RETURN_PROCESS")
public class ReturnProcess {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name="READER_ID")
    private Integer readerId;

    @JoinColumn(name="ITEM_ID")
    private Integer itemId;
}
