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
        this.capacity = capacity;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Room name is %s, Capacity is %s", name, capacity);
    }

}