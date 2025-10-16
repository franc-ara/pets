package com.domainchallenge.pets.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetCreatedDto {

    private UUID transactionId;
    private LocalDateTime dateCreated;
    private String status;
    private String name;

}
