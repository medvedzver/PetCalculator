package com.tractive.PetCalculator.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dogs")
public class Dog extends Pet {

    public Dog() {
        super();
    }

    public Dog(Integer ownerId, TrackerType trackerType, Boolean inZone) {
        super(PetType.DOG, ownerId, trackerType, inZone);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", petType=" + petType +
                ", ownerId=" + ownerId +
                ", trackerType=" + trackerType +
                ", inZone=" + inZone +
                '}';
    }
}
