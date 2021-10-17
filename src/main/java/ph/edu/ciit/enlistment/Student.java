package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class  Student {
    private final int studentNumber;
    private final Collection<Section> sections;
    private final Collection<Subject> subjectsTaken;

    Student(int studentNumber, Collection<Section> sections, Collection<Subject> subjectsTaken) {
        isTrue(studentNumber >= 0,"studentNumber must be non-zero, was : " + studentNumber);
        notNull(sections);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.subjectsTaken = new HashSet<>(subjectsTaken);
        this.studentNumber = studentNumber;
    }

    Student (int studentNumber) {
        this(studentNumber, Collections.emptyList(), Collections.emptyList());
    }

    void enlist(Section newSection) {
        notNull(newSection);
        /*compare the schedules of new section with the schedules of the existing sections,
        throw an exception if same schedule is found*/
        sections.forEach(currSection -> {
            if (currSection.hasScheduleConflict(newSection)) {
                throw new ScheduleConflictException("Current section " + currSection + " with schedule "
                    + currSection.getSchedule() + " has schedule conflict with new section " + newSection
                    + " at schedule " + newSection.getSchedule());
            }
            currSection.checkSameSubject(newSection);
        });

        newSection.checkPrereq(subjectsTaken);
        newSection.lock();
        try {
            newSection.incrementNumStudentEnlisted();
            sections.add(newSection);
        } finally {
            newSection.unlock();
        }
    }

    void delist(Section existingSection){
        notNull(existingSection);

        if (!sections.contains(existingSection)) throw new StudentDelistException("error delisting " + existingSection + ", does not exist");
        else sections.remove(existingSection);

    }

    Collection<Section> getSections() {
        return new ArrayList<>(sections);
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