package ph.edu.ciit.enlistment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

class StudentTest {

    @Test
    void enlist_two_sections_no_sked_conflict() {
        // Given a student and two sections w/ no sked conflict
        Student student  = new Student (1);
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("room1", 30), new Subject("sub1"), instructor);
        Section sec2 = new Section("B", new Schedule(Days.TF, new Period(Hours.H0830, Hours.H1130)), new Room("room1", 30), new Subject("sub2"), instructor);
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
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Instructor instructor2 = new Instructor(2, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("room1", 30), new Subject("sub1"), instructor);
        Section sec2 = new Section("B", new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("room2", 30), new Subject("sub1"), instructor2);

        // When student enlists in both sections
        student.enlist(sec1);
        // Then an exception should be thrown at second enlistment
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));

    }

    @Test
    void enlist_in_section_to_overcapacity() {
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Section section = new Section("A",new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("RM603", 1), new Subject("sub1"), instructor);
        Student student1  = new Student (1);
        Student student2  = new Student (2);

        student1.enlist(section);

        assertThrows(RoomOverCapacityException.class, () -> student2.enlist(section));
    }

    @Test
    void enlist_two_sections_same_subject(){
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Section section1= new Section("A",new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("RM603", 1), new Subject("sub1"), instructor);
        Section section2 = new Section("B",new Schedule(Days.TF, new Period(Hours.H0830, Hours.H1130)), new Room("RM604", 1), new Subject("sub1"), instructor);

        Student student1 = new Student(1);
        student1.enlist(section1);

        assertThrows(SameSubjectException.class, () -> student1.enlist(section2));
    }

    @Test
    void enlist_in_section_prerequisite_not_yet_taken(){
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Collection<Subject> prMath2 = new HashSet<>();
        prMath2.add(new Subject("math1"));

        Subject subjectMath2 = new Subject("math2", prMath2);
        Section section= new Section("A",new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("RM603", 1), subjectMath2, instructor);
        Student student1 = new Student(1);

        assertThrows(PrerequisiteException.class, () -> student1.enlist(section));
    }

    @Test
    void enlist_in_sections_overlapping_with_currently_enlisted_sections(){
        Student student1 = new Student(1);

        Instructor instructor = new Instructor(1, Collections.emptyList());
        Subject subjectMath = new Subject("math");
        Section section= new Section("A",new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("RM603", 1), subjectMath, instructor);
        student1.enlist(section);

        Instructor instructor2 = new Instructor(2, Collections.emptyList());
        Subject subjectEnglish = new Subject("english");
        Section section2= new Section("B",new Schedule(Days.MTH, new Period(Hours.H1000, Hours.H1130)), new Room("RM604", 1), subjectEnglish, instructor2);

        assertThrows(ScheduleConflictException.class, () -> student1.enlist(section2));
    }

    @Test
    void delist_unexisting_section(){
        Instructor instructor = new Instructor(1, Collections.emptyList());
        Student student1 = new Student(1);
        Section section = new Section("A",new Schedule(Days.MTH, new Period(Hours.H0830, Hours.H1130)), new Room("RM603", 1), new Subject("sub1"), instructor);

        assertThrows(StudentDelistException.class, () -> student1.delist(section));
    }



}
