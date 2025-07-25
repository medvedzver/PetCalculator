package com.tractive.PetCalculator.persistence;

import com.tractive.PetCalculator.persistence.model.TrackerType;

import java.util.Objects;

public class CountByTypeAndTracker {

    private Long count;
    private TrackerType trackerType;

    public CountByTypeAndTracker(Long count, TrackerType trackerType) {
        this.count = count;
        this.trackerType = trackerType;
    }

    public Long getCount() {
        return count;
    }

    public TrackerType getTrackerType() {
        return trackerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountByTypeAndTracker that = (CountByTypeAndTracker) o;
        return Objects.equals(count, that.count) && trackerType == that.trackerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, trackerType);
    }
}
