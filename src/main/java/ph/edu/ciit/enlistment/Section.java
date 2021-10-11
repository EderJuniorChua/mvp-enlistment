package ph.edu.ciit.enlistment;


import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.StringUtils.*;

class Section {
    private final String sectionId;
    private final Schedule schedule;
    private Room room;
    private Subject subject;
    //TODO: (Allen) 2. A section must have an instructor. A class Instructor is pre requisite.
    //TODO: (Allen) 3. An instructor cannot teach two or more sections with overlapping schedule. This is to be made in new instructor class.

    Section (String sectionId, Schedule schedule) {
        isBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was: " + sectionId);
        notNull(schedule);
        this.sectionId = sectionId;
        this.schedule = schedule;
    }

    Section (String sectionId, Schedule schedule, Room room) {
        isBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was: " + sectionId);
        this.sectionId = sectionId;
        this.schedule = schedule;
        this.room = room;
    }

    void checkForScheduleConflict(Section other) {
        if (this.schedule.equals(other.schedule)) {
            throw new ScheduleConflictException("current section: " + this +
                    " has same schedule with new section " + other +
                    " at schedule " + schedule);
        }
    }

    @Override
    public String toString() {
        return sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        return sectionId != null ? sectionId.equals(section.sectionId) : section.sectionId == null;
    }

    @Override
    public int hashCode() {
        return sectionId != null ? sectionId.hashCode() : 0;
    }

}