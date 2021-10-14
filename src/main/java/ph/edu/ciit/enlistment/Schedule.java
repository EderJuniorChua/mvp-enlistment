package ph.edu.ciit.enlistment;

import static org.apache.commons.lang3.Validate.*;

class Schedule {
    private final Days days;
    private final Period period;

    Schedule(Days days, Period period) {
        notNull(days);
        notNull(period);
        this.days = days;
        this.period = period;
    }

    @Override
    public String toString() {
        return days + " " + period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (days != schedule.days) return false;
        return period == schedule.period;
    }

    @Override
    public int hashCode() {
        int result = days != null ? days.hashCode() : 0;
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }

    //TODO: decide which exception to be called from this or the wrapper method
    boolean hasOverlap(Section other){
        if (this.equals(other.getSchedule())) {
            throw new ScheduleConflictException("current section: " + this +
                    " has same schedule with new section " + other +
                    " at schedule " + other.getSchedule());
        }
        return false;
    }

}

enum Days {
    MTH, TF, WS
}


enum Period {
    H0830, H1000, H1130, H1300, H1430, H1600
}