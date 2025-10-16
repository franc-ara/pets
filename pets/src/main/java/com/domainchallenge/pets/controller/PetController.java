package com.domainchallenge.pets.controller;

import com.domainchallenge.pets.controller.dto.PetCreatedDto;
import com.domainchallenge.pets.controller.dto.PetDto;
import com.domainchallenge.pets.service.IPetService;
import com.domainchallenge.pets.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class PetController {
    private static final Logger log = LoggerFactory.getLogger(PetController.class);

    private final IPetService iPetService;

    public PetController(IPetService iPetService) {
        this.iPetService = iPetService;
    }

    @GetMapping(value = "/pet/{petId}")
    public ResponseEntity<PetDto> getPet(@PathVariable("petId") Integer petId) {
        return ResponseEntity.ok(iPetService.getPetById(petId));

    }

    @PostMapping(value = "/pet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetCreatedDto> getPet(@Validated @RequestBody PetDto request) {
        PetCreatedDto response = iPetService.createPet(request);
        return ResponseEntity
                .created(URI.create(Constant.PET_RESOURCE.concat(response.getTransactionId().toString())))
                .body(response);
    }

}
