package ph.edu.ciit.enlistment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class InstructorTest {
    @Test
    void instructor_has_sections_with_overlapping_schedule(){
        Student student  = new Student (1);
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("room1", 30), new Subject("sub1"), instructor);

        assertThrows(ScheduleConflictException.class, () -> new Section("B", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("room1", 30), new Subject("sub2"), instructor));
    }
}
