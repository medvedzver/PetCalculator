package com.tractive.PetCalculator.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tractive.PetCalculator.persistence.model.PetType;
import com.tractive.PetCalculator.persistence.model.TrackerType;

import java.util.Objects;

public class PetDto {
    public final PetType petType;
    public final Integer ownerId;
    public final TrackerType trackerType;
    public final Boolean inZone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final Boolean lostTracker;

    public PetDto(PetType petType, Integer ownerId, TrackerType trackerType, Boolean inZone, Boolean lostTracker) {
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
        PetDto petDto = (PetDto) o;
        return petType == petDto.petType && Objects.equals(ownerId, petDto.ownerId) && trackerType == petDto.trackerType && Objects.equals(inZone, petDto.inZone) && Objects.equals(lostTracker, petDto.lostTracker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petType, ownerId, trackerType, inZone, lostTracker);
    }

    @Override
    public String toString() {
        return "PetDto{" +
                "petType=" + petType +
                ", ownerId=" + ownerId +
                ", trackerType=" + trackerType +
                ", inZone=" + inZone +
                ", lostTracker=" + lostTracker +
                '}';
    }
}
