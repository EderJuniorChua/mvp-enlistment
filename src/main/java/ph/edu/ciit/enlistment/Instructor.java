package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Instructor {
    private final int instructorID;
    private final Collection<Section> sections;

    Instructor(int instructorID, Collection<Section> sections) {
        notNull(sections);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.instructorID = instructorID;
    }

    Instructor(int instructorID) {
        this(instructorID, Collections.emptyList());
    }

    void procured_section(Section newSection) {
        notNull(newSection);
        sections.forEach(currSection -> currSection.hasScheduleConflict(newSection));
        sections.add(newSection);
    }
}