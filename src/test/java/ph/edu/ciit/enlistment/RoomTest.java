package ph.edu.ciit.enlistment;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void roomHasExpectedCapacity() {
        //Create a room with name RM603/604/605 and has 30 capacity
        Room room603 = new Room("RM603", 30);
        Room room604 = new Room("RM604", 35);
        Room room605 = new Room("RM605", 40);

        assertAll(
                () ->       assertEquals(30,room603.getCapacity()),
                () ->       assertEquals(35,room604.getCapacity()),
                () ->       assertEquals(40,room605.getCapacity())
        );
    }


}
