package ph.edu.ciit.enlistment;

import java.util.Objects;

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
        return days == schedule.days && period.equals(schedule.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, period);
    }

    boolean hasOverlap(Section other){
        return (this.equals(other.getSchedule()));
    }

}

enum Days {
    MTH, TF, WS
}

class Period{
    private final Hours start;
    private final Hours end;

    Period(Hours start, Hours end){
        this.start = start;
        this.end = end;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return start == period.start && end == period.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
enum Hours {
    //TODO: Add all the valid hours
    H0830, H0900, H0930, H1000, H1030, H1100, H1130, H1200, H1230, H1300, H1330, H1400, H1430, H1500, H1530, H1600, H1630, H1700, H1730

}