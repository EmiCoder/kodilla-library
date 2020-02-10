package com.crud.kodillalibrary.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {

    private Integer id;
    private String status;
    private Integer bookId;

}
