package com.tractive.PetCalculator.controller.dto;

import com.tractive.PetCalculator.persistence.model.PetType;
import com.tractive.PetCalculator.persistence.model.TrackerType;

import java.util.Map;
import java.util.Objects;

public class OutsideOfZoneDto {
    public final Map<PetType, Map<TrackerType, Long>> petTypeToTrackerTypeToOutsideValue;

    public OutsideOfZoneDto(Map<PetType, Map<TrackerType, Long>> petTypeToTrackerTypeToOutsideValue) {
        this.petTypeToTrackerTypeToOutsideValue = petTypeToTrackerTypeToOutsideValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutsideOfZoneDto that = (OutsideOfZoneDto) o;
        return Objects.equals(petTypeToTrackerTypeToOutsideValue, that.petTypeToTrackerTypeToOutsideValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petTypeToTrackerTypeToOutsideValue);
    }

    @Override
    public String toString() {
        return "OutsideOfZoneDto{" +
                "petTypeToTrackerTypeToOutsideValue=" + petTypeToTrackerTypeToOutsideValue +
                '}';
    }
}
