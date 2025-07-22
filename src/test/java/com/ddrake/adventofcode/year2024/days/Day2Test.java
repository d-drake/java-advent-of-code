package com.ddrake.adventofcode.year2024.days;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

public class Day2Test {
    private Day2 day2;

    @Before
    public void setUp() {
        day2 = new Day2();
    }

    @Test
    public void testExampleProblem() {
        // test with the example input
        day2.solveExample();
    }

    @Test
    public void testArrayIntegersMonotonicChecker() {
        // Test strictly increasing
        assertTrue(ArrayIntegersMonotonicChecker.isStrictlyMonotonic(Arrays.asList(1, 2, 3, 4, 5)));

        // Test strictly decreasing
        assertTrue(ArrayIntegersMonotonicChecker.isStrictlyMonotonic(Arrays.asList(5, 4, 3, 2, 1)));

        // Test not strictly monotonic (has duplicates)
        assertFalse(ArrayIntegersMonotonicChecker.isStrictlyMonotonic(Arrays.asList(1, 2, 2, 3)));

        // Test not monotonic
        assertFalse(ArrayIntegersMonotonicChecker.isStrictlyMonotonic(Arrays.asList(1, 3, 2, 4)));

        // Test single element
        assertTrue(ArrayIntegersMonotonicChecker.isStrictlyMonotonic(Arrays.asList(42)));

        // Test empty list
        assertTrue(ArrayIntegersMonotonicChecker.isStrictlyMonotonic(new ArrayList<>()));
    }

    @Test
    public void testArrayIntegersAdjacencyChecker() {
        // Test valid differences within range [1, 3]
        assertTrue(ArrayIntegersAdjacencyChecker.hasValidDifferences(
                Arrays.asList(7, 6, 4, 2, 1), 1, 3));

        // Test invalid differences (too large)
        assertFalse(ArrayIntegersAdjacencyChecker.hasValidDifferences(
                Arrays.asList(1, 2, 7, 8, 9), 1, 3));

        // Test invalid differences (too small)
        assertFalse(ArrayIntegersAdjacencyChecker.hasValidDifferences(
                Arrays.asList(1, 1, 2, 3), 1, 3));

        // Test single element
        assertTrue(ArrayIntegersAdjacencyChecker.hasValidDifferences(
                Arrays.asList(42), 1, 3));

        // Test empty list
        assertTrue(ArrayIntegersAdjacencyChecker.hasValidDifferences(
                new ArrayList<>(), 1, 3));
    }
}
