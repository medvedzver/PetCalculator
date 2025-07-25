package com.tractive.PetCalculator.persistence;

import com.tractive.PetCalculator.persistence.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatsRepository extends JpaRepository<Cat, Integer> {

    List<Cat> findByOwnerId(Integer ownerId);

    @Query("SELECT new com.tractive.PetCalculator.persistence.CountByTypeAndTracker(COUNT(c.id), c.trackerType) " +
            " FROM Cat as c WHERE c.inZone=:inZone " +
            " GROUP BY c.trackerType")
    List<CountByTypeAndTracker> countByInZone(Boolean inZone);
}
