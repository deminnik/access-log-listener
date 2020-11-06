package deminnik.accessloglistener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppOptionsTest {
    private AppOptions appOptions;
    private final double accessible = 95.5;
    private final double time = 45;

    @BeforeEach
    void setUp() {
        String[] args = {"-u", String.valueOf(this.accessible), "-t", String.valueOf(this.time)};
        this.appOptions = new AppOptions(args);
    }

    @Test
    void testGetAccessible() {
        double expected = this.accessible;
        double actual = this.appOptions.getAccessible();
        assertEquals(expected, actual);
    }

    @Test
    void testGetTime() {
        double expected = this.time;
        double actual = this.appOptions.getTime();
        assertEquals(expected, actual);
    }
}