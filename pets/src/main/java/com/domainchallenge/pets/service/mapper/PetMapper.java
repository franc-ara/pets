package com.domainchallenge.pets.service.mapper;

import com.domainchallenge.pets.controller.dto.PetDto;
import com.domainchallenge.pets.domain.Pet;
import com.domainchallenge.pets.utils.Constant;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constant.SPRING)
public interface PetMapper {

     PetDto petToDto(Pet pet);
     Pet dtoToPet(PetDto dto);
}
