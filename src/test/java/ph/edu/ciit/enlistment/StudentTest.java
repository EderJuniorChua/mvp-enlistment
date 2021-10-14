package ph.edu.ciit.enlistment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

class StudentTest {

    @Test
    void enlist_two_sections_no_sked_conflict() {
        // Given a student and two sections w/ no sked conflict
        Student student  = new Student (1);

        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("room1", 30), new Subject("sub1"));
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H1000), new Room("room1", 30), new Subject("sub1"));
        // When the student enlists in both sections
        student.enlist(sec1);
        student.enlist(sec2);
        // Then the student will have those two sections & no other
        Collection<Section> sections = student.getSections();
        assertAll(
                () ->       assertTrue(sections.containsAll(List.of(sec1,sec2))),
                () ->       assertEquals(2,sections.size())
        );
        assertEquals(2,sections.size());


    }

    @Test
    void enlist_two_sections_same_sked() {
        // Given a student and two sections with same sked
        Student student  = new Student (1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830), new Room("room1", 30), new Subject("sub1"));
        Section sec2 = new Section("B", new Schedule(Days.MTH, Period.H0830), new Room("room2", 30), new Subject("sub1"));

        // When student enlists in both sections
        student.enlist(sec1);
        // Then an exception should be thrown at second enlistment
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));

    }

    @Test
    void enlist_in_section_to_overcapacity() {
        Section section = new Section("A",new Schedule(Days.MTH, Period.H0830), new Room("RM603", 1), new Subject("sub1"));
        Student student1  = new Student (1);
        Student student2  = new Student (2);

        student1.enlist(section);

        assertThrows(RoomOverCapacityException.class, () -> student2.enlist(section));
    }

    @Test
    void delist_unexisting_section(){
        Student student1 = new Student(1);
        Section section = new Section("A",new Schedule(Days.MTH, Period.H0830), new Room("RM603", 1), new Subject("sub1"));

        assertThrows(StudentDelistException.class, () -> student1.delist(section));
    }
}
