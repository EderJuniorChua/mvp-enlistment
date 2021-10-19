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
                (this.period.startHourOverlapsWith(other.period) ||
                this.period.endHourOverlapsWith(other.period)));
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

        //Todo: Allen - Resolve issue #3
        if (ends < starts)
        {
            throw new InvalidPeriodException(String.format("Period End (%s) is before Period Start (%s).", start, end));
        }

    }

    //hasOverlap() - return true when given period is overlapping with other period
    boolean startHourOverlapsWith(Period other){
        return other.end.ordinal() > this.start.ordinal() &&
                this.start.ordinal() >= other.start.ordinal();
    }
    boolean endHourOverlapsWith(Period other){
        return other.start.ordinal() < this.end.ordinal() &&
                this.end.ordinal() <= other.end.ordinal();
    }

    public Hours getStart() {
        return Hours.values()[start.ordinal()];
    }

    public Hours getEnd() {
        return Hours.values()[end.ordinal()];
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