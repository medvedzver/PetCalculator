package com.tractive.PetCalculator.persistence.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public PetType petType;
    public Integer ownerId;
    public TrackerType trackerType;
    public Boolean inZone;

    public Pet() {
    }

    public Pet(PetType petType, Integer ownerId, TrackerType trackerType, Boolean inZone) {
        this.petType = petType;
        this.ownerId = ownerId;
        this.trackerType = trackerType;
        this.inZone = inZone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && petType == pet.petType && Objects.equals(ownerId, pet.ownerId) && trackerType == pet.trackerType && Objects.equals(inZone, pet.inZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petType, ownerId, trackerType, inZone);
    }
}
