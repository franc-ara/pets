package com.domainchallenge.pets.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PetDto {

    @NonNull
    private Integer id;
    private String status;
    private String name;
}
