package com.tractive.PetCalculator.service;

import com.tractive.PetCalculator.controller.dto.CreatePetDto;
import com.tractive.PetCalculator.controller.dto.OutsideOfZoneDto;
import com.tractive.PetCalculator.controller.dto.PetDto;
import com.tractive.PetCalculator.exception.PetCreationException;
import com.tractive.PetCalculator.persistence.model.Cat;
import com.tractive.PetCalculator.persistence.model.Dog;
import com.tractive.PetCalculator.persistence.model.TrackerType;
import com.tractive.PetCalculator.persistence.*;
import com.tractive.PetCalculator.persistence.model.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PetsService {

    @Autowired
    public CatsRepository catsRepository;

    @Autowired
    public DogsRepository dogsRepository;

    public void createPet(CreatePetDto petDto) throws PetCreationException {
        if(petDto.petType == PetType.CAT) {
            catsRepository.save(toCat(petDto));
        } else if(petDto.petType == PetType.DOG) {
            dogsRepository.save(toDog(petDto));
        } else {
            throw new PetCreationException("Unsupported pet type");
        }
    }

    public List<PetDto> findByOwnerId(Integer ownerId) {
        return Stream.concat(
                catsRepository.findByOwnerId(ownerId).stream().map(this::fromCat),
                dogsRepository.findByOwnerId(ownerId).stream().map(this::fromDog)
        ).toList();
    }

    public List<PetDto>  findAll() {
        return Stream.concat(
                catsRepository.findAll().stream().map(this::fromCat),
                dogsRepository.findAll().stream().map(this::fromDog)
        ).toList();
    }

    public OutsideOfZoneDto findOutsideOfZone() {
        List<CountByTypeAndTracker> catsOutOfZone  = catsRepository.countByInZone(false);
        List<CountByTypeAndTracker> dogsOutOfZone  = dogsRepository.countByInZone(false);
        return new OutsideOfZoneDto(
                Map.of(
                        PetType.CAT, toMap(catsOutOfZone),
                        PetType.DOG, toMap(dogsOutOfZone)
                )
        );
    }

    private Cat toCat(CreatePetDto petDto) throws PetCreationException {
        if(petDto.ownerId == null || petDto.trackerType == null ||
                petDto.inZone == null || petDto.lostTracker == null
        ) {
            throw new PetCreationException("All params must be set");
        }
        if(petDto.trackerType == TrackerType.MEDIUM) {
            throw new PetCreationException("Cats can't have medium trackers");
        }
        return new Cat(
            petDto.ownerId,
            petDto.trackerType,
            petDto.inZone,
            petDto.lostTracker
        );
    }

    private PetDto fromCat(Cat cat) {
        return new PetDto(
                PetType.CAT,
                cat.ownerId,
                cat.trackerType,
                cat.inZone,
                cat.lostTracker
        );
    }

    private Dog toDog(CreatePetDto petDto) throws PetCreationException {
        if(petDto.ownerId == null || petDto.trackerType == null ||
                petDto.inZone == null) {
            throw new PetCreationException("All params must be set");
        }
        return new Dog(
                petDto.ownerId,
                petDto.trackerType,
                petDto.inZone
        );
    }

    private PetDto fromDog(Dog dog) {
        return new PetDto(
                PetType.DOG,
                dog.ownerId,
                dog.trackerType,
                dog.inZone,
                null
        );
    }

    private Map<TrackerType, Long> toMap(List<CountByTypeAndTracker> countByTypeAndTracker) {
        return countByTypeAndTracker.stream().collect(Collectors.toMap(CountByTypeAndTracker::getTrackerType, CountByTypeAndTracker::getCount));
    }
}
