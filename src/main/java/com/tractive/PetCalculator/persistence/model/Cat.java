package com.tractive.PetCalculator.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat extends Pet {

    public Boolean lostTracker;

    public Cat() {
        super();
    }

    public Cat(Integer ownerId, TrackerType trackerType, Boolean inZone, Boolean lostTracker) {
        super(PetType.CAT, ownerId, trackerType, inZone);
        this.lostTracker = lostTracker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return Objects.equals(lostTracker, cat.lostTracker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lostTracker);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "lostTracker=" + lostTracker +
                ", id=" + id +
                ", petType=" + petType +
                ", ownerId=" + ownerId +
                ", trackerType=" + trackerType +
                ", inZone=" + inZone +
                '}';
    }
}
