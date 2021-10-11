package ph.edu.ciit.enlistment;

import java.util.*;

import static org.apache.commons.lang3.Validate.*;

class Instructor {
    private final int ins_ID;
    private final Collection<Section> sections;

    Instructor(int ins_ID, Collection<Section> sections) {
        notNull(sections);
        this.sections = new HashSet<>(sections);
        this.sections.removeIf(Objects::isNull);
        this.ins_ID = ins_ID;
    }

    Instructor(int ins_ID) {
        this(ins_ID, Collections.emptyList());
    }

    void procured_section(Section newSection) {
        notNull(newSection);
        sections.forEach(currSection -> currSection.checkForScheduleConflict(newSection));
        sections.add(newSection);
    }
}