package com.domainchallenge.pets.service;

import com.domainchallenge.pets.controller.dto.PetCreatedDto;
import com.domainchallenge.pets.controller.dto.PetDto;

public interface IPetService {

    PetDto getPetById(Integer id);

    PetCreatedDto createPet(PetDto petDto);
}
