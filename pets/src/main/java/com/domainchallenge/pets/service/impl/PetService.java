package com.domainchallenge.pets.service.impl;

import com.domainchallenge.pets.controller.dto.PetCreatedDto;
import com.domainchallenge.pets.controller.dto.PetDto;
import com.domainchallenge.pets.domain.Pet;
import com.domainchallenge.pets.service.IPetService;
import com.domainchallenge.pets.service.application.port.rest.PetClient;
import com.domainchallenge.pets.service.mapper.PetMapper;
import com.domainchallenge.pets.utils.Constant;
import com.domainchallenge.pets.utils.exception.PetNotFoundException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PetService implements IPetService {
    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private PetClient petClient;
    private PetMapper petMapper;

    public PetService(PetMapper petMapper, PetClient petClient) {
        this.petClient = petClient;
        this.petMapper = petMapper;
    }

    @Override
    public PetDto getPetById(Integer id) {
        UUID transactionId = createTransactionId(id);
        try {
            return petMapper.petToDto(petClient.getPetById(id));
        } catch (FeignException e) {
            log.error(e.getMessage());
            throw new PetNotFoundException(Constant.PET_NOT_FOUND_MESSAGE, transactionId);
        }
    }

    @Override
    public PetCreatedDto createPet(PetDto petDto) {
        UUID transactionId = createTransactionId(petDto);

        Pet petRequest = petMapper.dtoToPet(petDto);
        Pet pet = petClient.createPet(petRequest);
        log.info(Constant.PET_CREATED, pet);

        //me di cuenta muy tarde que el campo status es un boolean en este proyecto
        //en el api swagger es un string, lo mapeo como viene del api pero si debí revisar ese punto
        return PetCreatedDto.builder()
                .name(pet.getName())
                .dateCreated(LocalDateTime.now())
                .status(pet.getStatus())
                .transactionId(transactionId).build();
    }

    private UUID createTransactionId(Object o) {
        UUID transactionId = UUID.randomUUID();
        log.info(Constant.INFO_REQUEST, transactionId, o);
        return transactionId;
    }


    //Registro la excepción en el catch para conocer de manera interna que paso en la petición
    //el usuario no necesita conocer los detalles del error, simplemente que el id no existe
    //por normas de código seguro se recomienda no dar mas información de la necesaria
    //incluso dar la longitud de un campo, el formato u otros motivos de algún bad request
    //dependiendo el escenario podria considerarse inseguro
    //porque se le va dando información a algún usuario mal intencionado
    //en escenarios más complejos se podría manejar un id del error con un catalogo interno de errores
    //así sabemos que le pasa a la petición del usuario sin rebelar detalles
    //en un proyecto real lo ideal seria ir guardando los logs en un archivo
    //para el monitoreo posterior o dependiendo el proyecto existen herramientas de nube
    //que persisten la información
    //el post del api swager no regresa error a pesar de pedir el id como obligatorio
    //por eso no hay un control ahí, sin embargo en un contrato bien establecido
    //debo tomar el campo obligatorio en cuenta, desde el ingreso a la api que estoy desarrollando
    //para evitar llegar a la capa de negocio si no se cumplen con los campos obligatorios de la petición
}
