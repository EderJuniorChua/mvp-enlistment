package ph.edu.ciit.enlistment;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SectionTest {
    @Test
    void sections_have_same_room_but_overlapping_schedules() {
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Room room1 = new Room("room1", 30);
        Section sec1 = new Section("A", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), room1, new Subject("sub1"), instructor);

        assertThrows(ScheduleConflictException.class, () -> new Section("B", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), room1, new Subject("sub1"), instructor));
    }
}
