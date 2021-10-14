package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Instructor {
    private int instructorID;
    private Schedule schedule;
    private Collection<Section> sections;

    Instructor(int instructorID, Collection<Section> sections) {
        notNull(sections);
        isTrue(instructorID >= 0,"instructorID must be non-zero, was : " + instructorID);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.instructorID = instructorID;
        this.schedule = schedule;
    }

    //remove this and change Test
//    Instructor(int instructorID, int instructorID1, Schedule schedule, Collection<Section> sections) {
//        this(instructorID, Collections.emptyList());
//    }
//
//    public <T> Instructor(long instructorID, Collection<T> emptyList) {
//    }

    void assign(Section newSection) {
        notNull(newSection);
        /*compare the schedules of new section with the schedules of the existing sections,
        throw an exception if same schedule is found*/
        sections.forEach(currSection -> {
            if (currSection.hasScheduleConflict(newSection)) {
                throw new ScheduleConflictException("New section " + newSection +
                        " has a schedule conflict with current section " + currSection +
                        " for instructor " + this);
            }
        });
        sections.add(newSection);
    }
}

