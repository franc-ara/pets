package com.domainchallenge.pets.service.application.port.rest;

import com.domainchallenge.pets.controller.dto.PetDto;
import com.domainchallenge.pets.domain.Pet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pet-client", url = "${api.pets.get.byid}")
public interface PetClient {

    @GetMapping("/pet/{id}")
    Pet getPetById(@PathVariable("id") Integer id);

    @PostMapping("/pet")
    Pet createPet(@RequestBody Pet pet);
}
