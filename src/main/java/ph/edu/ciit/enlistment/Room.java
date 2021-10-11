package ph.edu.ciit.enlistment;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.Validate.*;

class Room {
    private final String name;
    private final int capacity;

    Room(String name, int capacity) {
        isBlank(name);
        isTrue(StringUtils.isAlphanumeric(name),
                "Room name must be alphanumeric, was: " + name);
        isTrue(capacity>0, "Room Capacity must be greater than zero. It was: "+capacity);
        this.name = name;
        this.capacity = capacity;
    }

    Room getRoom(){
        return new Room(this.name, this.capacity);
    }

    //if my memory serves me right, a variable is okay to have getter and still encapsulated
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

    void reserve(Sectionn newSection) {
        sections.forEach(currSection -> {
            if (currSection.hasScheduleConflict(newSection)) {
                throw new ScheduleConflictException("New section " + newSection +
                        " has a schedule conflict with current section " + currSection +
                        " for room " + this);
            }
        });
    }
    void checkCapacity(int occupancy){
        if (occupancy >= capacity) {
            throw new SectionCapacityException(
                    "occupancy of " + occupancy + " is at or exceeds capacity of " + capacity);
            )
        }
    }


}