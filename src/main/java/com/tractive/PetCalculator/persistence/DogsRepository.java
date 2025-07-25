package com.tractive.PetCalculator.persistence;

import com.tractive.PetCalculator.persistence.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogsRepository extends JpaRepository<Dog, Integer> {

    List<Dog> findByOwnerId(Integer ownerId);

    @Query("SELECT new com.tractive.PetCalculator.persistence.CountByTypeAndTracker(COUNT(d.id), d.trackerType) " +
            " FROM Dog as d WHERE d.inZone=:inZone " +
            " GROUP BY d.trackerType")
    List<CountByTypeAndTracker> countByInZone(Boolean inZone);
}
