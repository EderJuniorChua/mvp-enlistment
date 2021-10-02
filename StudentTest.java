package ph.edu.ciit.enlistment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

class StudentTest {

    @Test
    void enlist_two_sections_no_sked_conflict() {
        // Given a student and two sections w/ no sked conflict
        Student student  = new Student (1, Collections.emptyList());
        Section sec1 = new Section("A");
        Section sec2 = new Section("B");
        // When the student enlists in both sections
        student.enlist(sec1);
        student.enlist(sec2);
        // Then the student will have those two sections & no other

    }
}
