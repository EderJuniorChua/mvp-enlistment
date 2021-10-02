package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Student {
    private final int studentNumber;
    private final Collection<Section> sections;

    Student(int studentNumber, Collection<Section> sections) {
        isTrue(studentNumber >= 0,"studentNumber must be non-zero, was : " + studentNumber);
        notNull(sections);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.studentNumber = studentNumber;
    }

    void enlist(Section section) {
        notNull(section);

        sections.add(section);
    }

    Collection<Section> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return "Student #" + studentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return studentNumber == student.studentNumber;
    }

    @Override
    public int hashCode() {
        return studentNumber;
    }
}