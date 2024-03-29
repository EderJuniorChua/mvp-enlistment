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

    boolean hasOverlap(Schedule other){
        return (this.days.equals(other.days) &&
                !(this.period.isBefore(other.period)||
                  this.period.isAfter(other.period)) );
    }

}

enum Days {
    MTH, TF, WS
}

class Period{
    private final Hours start;
    private final Hours end;

    Period(Hours start, Hours end){
        notNull(start);
        notNull(end);
        //https://jvm-gaming.org/t/beware-of-enum-values/41208
        //https://dzone.com/articles/memory-hogging-enumvalues-method
        //Hours.values() = immutable newly copied array (defensive copy)
        this.start = Hours.values()[start.ordinal()];
        this.end = Hours.values()[end.ordinal()];

        int starts = start.ordinal();
        int ends = end.ordinal();

        if (ends <= starts)
        {
            throw new InvalidPeriodException(String.format("Period End (%s) is before Period Start (%s).", start, end));
        }

    }

    boolean isBefore (Period other) {
        return this.end.ordinal() <= other.start.ordinal();
    }
    boolean isAfter(Period other) {
        return this.start.ordinal() >= other.end.ordinal();
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
    H0830, H0900, H0930, H1000, H1030, H1100, H1130, H1200, H1230, H1300, H1330, H1400, H1430, H1500, H1530, H1600, H1630, H1700, H1730
}