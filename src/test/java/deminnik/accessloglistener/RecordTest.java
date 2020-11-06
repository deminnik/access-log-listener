package deminnik.accessloglistener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {
    private Record record;
    private GregorianCalendar startTime;
    private GregorianCalendar finishTime;

    @BeforeEach
    void setUp() {
        this.startTime = new GregorianCalendar(2020, 11, 6, 13, 10, 11);
        this.finishTime = new GregorianCalendar(2020, 11, 6, 13, 12, 54);
        this.record = new Record(95.5, this.startTime, this.finishTime);
    }

    @Test
    void testGetStartTime() {
        Calendar expected = this.startTime;
        Calendar actual = this.record.getStartTime();
        assertTrue(expected.compareTo(actual) == 0);
    }

    @Test
    void testGetFinishTime() {
        Calendar expected = this.finishTime;
        Calendar actual = this.record.getFinishTime();
        assertTrue(expected.compareTo(actual) == 0);
    }

    @Test
    void testGetAccessible() {
        double expected = 95.5;
        double actual = this.record.getAccessible();
        assertEquals(expected, actual);
    }
}