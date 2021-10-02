package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Room {
    private final String name;
    private final int capacity;

    Room (String name) {
        isBlank(name);
        isTrue(StringUtils.isAlphanumeric(name),
                "Room name must be alphanumeric, was: " + name);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Room name is %s, Capacity is %s", name, capacity);
    }

}