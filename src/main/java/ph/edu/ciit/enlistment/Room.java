package ph.edu.ciit.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.Validate.*;

class Room {
    private final String name;
    private final int capacity;
    private final Collection<Section> sections;

    Room(String name, int capacity, Collection<Section> sections) {
        isBlank(name);
        isTrue(StringUtils.isAlphanumeric(name),
                "Room name must be alphanumeric, was: " + name);
        isTrue(capacity>0, "Room Capacity must be greater than zero. It was: "+capacity);
        this.name = name;
        this.capacity = capacity;
        notNull(sections);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
    }

    Room (String name, int capacity) {
        this(name, capacity, Collections.emptyList());
    }

    int getCapacity(){
        return capacity;
    }

    @Override
    public String toString() {
        return String.format("Room name is %s, Capacity is %s", name, capacity);
    }

    void reserve(Section newSection) {
        notNull(newSection);
        /*compare the schedules of new section with the schedules of the existing sections,
        throw an exception if same schedule is found*/
        sections.forEach(currSection -> {
            if (currSection.hasScheduleConflict(newSection)) {
                throw new ScheduleConflictException("New section " + newSection +
                        " has a schedule conflict with current section " + currSection +
                        " for room " + this);
            }
        });
        sections.add(newSection);
    }

    void checkCapacity(int occupancy){
        if (occupancy >= capacity) {
            throw new RoomOverCapacityException(
                    "occupancy of " + occupancy + " is at or exceeds capacity of " + capacity);

        }
    }

}