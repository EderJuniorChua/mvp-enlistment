package ph.edu.ciit.enlistment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SectionTest {
    @Test
    void sections_have_same_room_but_overlapping_schedules(){
        Room room1 = new Room("room1", 30);
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), room1);

        assertThrows(ScheduleConflictException.class, () -> new Section("B", new Schedule(Days.MTH, Period.H0830), room1));
    }
}
