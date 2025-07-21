package com.ddrake.adventofcode.year2024.days;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class Day1Test {
    private Day1 day1;

    @Before
    public void setUp() {
        day1 = new Day1();
    }

    @Test
    public void testDayNumber() {
        assertEquals(1, day1.getDayNumber());
    }

    @Test
    public void testInputFileName() {
        assertEquals("day1.txt", day1.getInputFileName());
    }

    @Test
    public void testSolveDoesNotThrow() {
        // Just verify solve() runs without exceptions
        day1.solve();
    }

    @Test
    public void testExampleProblem() {
        // test with the example input
        day1.solveExample();
    }
}