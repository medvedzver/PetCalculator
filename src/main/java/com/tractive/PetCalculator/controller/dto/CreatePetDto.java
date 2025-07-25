package com.tractive.PetCalculator.controller.dto;

import com.tractive.PetCalculator.persistence.model.PetType;
import com.tractive.PetCalculator.persistence.model.TrackerType;

import java.util.Objects;

public class CreatePetDto {
    public final PetType petType;
    public final Integer ownerId;
    public final TrackerType trackerType;
    public final Boolean inZone;
    public final Boolean lostTracker;

    public CreatePetDto(PetType petType, Integer ownerId, TrackerType trackerType, Boolean inZone, Boolean lostTracker) {
        this.petType = petType;
        this.ownerId = ownerId;
        this.trackerType = trackerType;
        this.inZone = inZone;
        this.lostTracker = lostTracker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePetDto that = (CreatePetDto) o;
        return petType == that.petType && Objects.equals(ownerId, that.ownerId) && trackerType == that.trackerType && Objects.equals(inZone, that.inZone) && Objects.equals(lostTracker, that.lostTracker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petType, ownerId, trackerType, inZone, lostTracker);
    }
}
