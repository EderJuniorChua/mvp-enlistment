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

    void reserve(Section newSection) {
        notNull(newSection);
        /*compare the schedules of new section with the schedules of the existing sections,
        throw an exception if same schedule is found*/
        sections.forEach(currSection -> currSection.checkForScheduleConflict(newSection));
        sections.add(newSection);
    }

    Room getRoom(){
        return new Room(this.name, this.capacity, this.sections);
    }

    int getCapacity(){
        return capacity;
    }

    @Override
    public String toString() {
        return String.format("Room name is %s, Capacity is %s", name, capacity);
    }

    //TODO: 4th bullet, may not exceed the capacity of the room. (can be copied from enlist())
    void checkRoomOverCapacity(int studentsToEnlist) {
        if (this.capacity < studentsToEnlist) {
            throw new RoomOverCapacityException(
                    String.format("current room: %s has capacity: %s but is trying to enlist: %d",
                                    this, capacity, studentsToEnlist));
        }

    }

    void reserve(Section newSection) {
        sections.forEach(currSection -> {
            if (currSection.checkForScheduleConflict(newSection)) {
                throw new ScheduleConflictException("New section " + newSection +
                        " has a schedule conflict with current section " + currSection +
                        " for room " + this);
            }
        });
    }
    void checkCapacity(int occupancy){
        if (occupancy >= capacity) {
            throw new RoomOverCapacityException(
                    "occupancy of " + occupancy + " is at or exceeds capacity of " + capacity);

        }
    }


}