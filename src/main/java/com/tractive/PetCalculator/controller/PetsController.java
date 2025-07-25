package com.tractive.PetCalculator.controller;

import com.tractive.PetCalculator.controller.dto.CreatePetDto;
import com.tractive.PetCalculator.controller.dto.OutsideOfZoneDto;
import com.tractive.PetCalculator.controller.dto.PetDto;
import com.tractive.PetCalculator.exception.PetCreationException;
import com.tractive.PetCalculator.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    public PetsService petsService;

    @GetMapping("")
    public ResponseEntity<List<PetDto>> findByOwnerId(@RequestParam(name = "ownerId", required = false) Integer ownerId) {
        List<PetDto> result;
        if(ownerId != null) {
            result = petsService.findByOwnerId(ownerId);
        } else {
            result = petsService.findAll();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/outofzone")
    public ResponseEntity<OutsideOfZoneDto> findPetsOutsideOfZone() {
        return ResponseEntity.ok(petsService.findOutsideOfZone());
    }

    @PostMapping
    public ResponseEntity<String> addPet(@RequestBody CreatePetDto createPetDto) {
        try {
            petsService.createPet(createPetDto);
        } catch (PetCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
