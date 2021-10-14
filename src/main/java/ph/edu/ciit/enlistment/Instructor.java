package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Instructor {
    private final int instructorID;
    private final Schedule schedule;
    private final Collection<Section> sections;
    Instructor(int instructorID, Schedule schedule, Collection<Section> sections) {
        notNull(sections);
        isTrue(instructorID >= 0,"instructorID must be non-zero, was : " + instructorID);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.instructorID = instructorID;
        this.subject = subject;
    }
    Instructor(int instructorID) {
        this(instructorID, Collections.emptyList());
    }
    void procured_section(Section newSection) {
        notNull(newSection);
        if (this.sections.isSimilar(other.sections))
        throw new SameSubjectException(
                    "This section" + this + "and other section " + other + " have same schedule."
            );
    }

}