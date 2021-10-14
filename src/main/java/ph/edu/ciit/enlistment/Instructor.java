package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Instructor {
    private int instructorID;
    private Schedule schedule;
    private Collection<Section> sections;
    Instructor(int instructorID, Collection<Section> sections, Schedule schedule) {
        notNull(sections);
        isTrue(instructorID >= 0,"instructorID must be non-zero, was : " + instructorID);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.instructorID = instructorID;
        this.schedule = schedule;
    }
    Instructor(int instructorID, int instructorID1, Schedule schedule, Collection<Section> sections) {
        this(instructorID, Collections.emptyList());
    }

    public <T> Instructor(long instructorID, Collection<T> emptyList) {
    }

    boolean hasScheduleConflict(Section other) {
        notNull(other);
        return this.schedule.hasOverlap(other);
    }
    }

