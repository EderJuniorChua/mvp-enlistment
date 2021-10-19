package ph.edu.ciit.enlistment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodTest {
    @Test
    void create_period_with_end_time_before_start_time(){
        assertThrows(InvalidPeriodException.class, () -> new Period(Hours.H1130, Hours.H0830));
    }
}
