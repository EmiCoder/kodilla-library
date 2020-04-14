package com.crud.kodillalibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnProcessDTO {

    private Integer id;
    private Integer readerId;
    private Integer itemId;
}
