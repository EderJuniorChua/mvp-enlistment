package ph.edu.ciit.enlistment;


import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.locks.*;

import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.StringUtils.*;

class Section {
    private final String sectionId;
    private final Schedule schedule;
    private int numStudentsEnlisted = 0;
    private Room room;
    private Subject subject;
    private final ReentrantLock lock = new ReentrantLock();
    //TODO: (Allen) 2. A section must have an instructor. A class Instructor is pre requisite.
    //TODO: (Allen) 3. An instructor cannot teach two or more sections with overlapping schedule. This is to be made in new instructor class.

//    Section (String sectionId, Schedule schedule) {
//        this(sectionId, schedule, null);
//    }

    Section (String sectionId, Schedule schedule, Room room, Subject subject) {
        isBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was: " + sectionId);
        this.sectionId = sectionId;
        notNull(schedule);
        this.schedule = schedule;
        room.reserve(this);
        this.room = room;
        this.subject = subject;
    }

    void checkSameSubject(Section other){
        notNull(other);
        if (this.subject.equals(other.subject)) {
            throw new SameSubjectException(
                    "This section " + this + "and other section " + other + " have same subject"
            );
        }
    }

    boolean hasScheduleConflict(Section other) {
        notNull(other);
        return this.schedule.hasOverlap(other);
    }

    @Override
    public String toString() {
        return sectionId;
    }

    Schedule getSchedule() { return schedule; }

    void checkPrereq(Collection<Subject> subjectsTaken) {
        notNull(subjectsTaken);
        subject.checkPrereqs(subjectsTaken);
    }

    void incrementNumStudentEnlisted() {
        room.checkCapacity(numStudentsEnlisted);
        numStudentsEnlisted++;
    }

    void lock() { lock.lock(); }
    void unlock() { lock.unlock(); }

    void decrementNumStudentEnlisted() { numStudentsEnlisted--; }
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