package com.ddrake.adventofcode.year2024.days;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Method;

public class Day4Test {
    private Day4 day4;

    @Before
    public void setUp() {
        day4 = new Day4();
    }

    @Test
    public void testExampleProblem() {
        // test with the example input
        day4.solveExample();
    }

    @Test
    public void testConvertInputTo2DArray() throws Exception {
        Method method = Day4.class.getDeclaredMethod("convertInputTo2DArray", List.class);
        method.setAccessible(true);

        List<String> input = Arrays.asList("XMAS", "ABCD", "EFGH");
        char[][] result = (char[][]) method.invoke(day4, input);

        assertEquals(3, result.length);
        assertEquals(4, result[0].length);
        assertArrayEquals(new char[] { 'X', 'M', 'A', 'S' }, result[0]);
        assertArrayEquals(new char[] { 'A', 'B', 'C', 'D' }, result[1]);
        assertArrayEquals(new char[] { 'E', 'F', 'G', 'H' }, result[2]);
    }

    @Test
    public void testTwoDimArrayStringCheckerHorizontal() {
        char[][] grid = {
                { 'X', 'M', 'A', 'S', 'A' },
                { 'A', 'S', 'A', 'M', 'X' },
                { 'X', 'M', 'A', 'S', 'B' }
        };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("XMAS", grid);
        Integer count = checker.countAllInstancesOfString();

        // Should find XMAS in:
        // Row 0: forward at position 0
        // Row 1: backward at position 4
        // Row 2: forward at position 0
        assertTrue(count >= 3);
    }

    @Test
    public void testTwoDimArrayStringCheckerVertical() {
        char[][] grid = {
                { 'X', 'A', 'B' },
                { 'M', 'B', 'C' },
                { 'A', 'C', 'D' },
                { 'S', 'D', 'E' }
        };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("XMAS", grid);
        Integer count = checker.countAllInstancesOfString();

        // Should find XMAS vertically in column 0
        assertTrue(count >= 1);
    }

    @Test
    public void testTwoDimArrayStringCheckerDiagonal() {
        char[][] grid = {
                { 'X', 'A', 'B', 'C' },
                { 'A', 'M', 'B', 'C' },
                { 'B', 'B', 'A', 'C' },
                { 'C', 'B', 'C', 'S' }
        };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("XMAS", grid);
        Integer count = checker.countAllInstancesOfString();

        // Should find XMAS diagonally from (0,0) to (3,3)
        assertTrue(count >= 1);
    }

    @Test
    public void testTwoDimArrayStringCheckerEmpty() {
        char[][] grid = {
                { 'A', 'B', 'C' },
                { 'D', 'E', 'F' },
                { 'G', 'H', 'I' }
        };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("XMAS", grid);
        Integer count = checker.countAllInstancesOfString();

        // Should find no XMAS
        assertEquals(Integer.valueOf(0), count);
    }

    @Test
    public void testTwoDimArrayStringCheckerSingleChar() {
        char[][] grid = { { 'X' } };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("X", grid);
        Integer count = checker.countAllInstancesOfString();

        // Should find single X
        assertEquals(Integer.valueOf(1), count);
    }

    @Test
    public void testTwoDimArrayStringCheckerOverlapping() {
        char[][] grid = {
                { 'X', 'M', 'A', 'S', 'X', 'M', 'A', 'S' },
                { 'S', 'A', 'M', 'X', 'M', 'A', 'S', 'A' }
        };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("XMAS", grid);
        Integer count = checker.countAllInstancesOfString();

        // Should find multiple overlapping instances
        assertTrue(count >= 2);
    }

    @Test
    public void testTwoDimArrayStringCheckerEdgeCases() {
        // Test grid smaller than search string
        char[][] smallGrid = {
                { 'X', 'M' },
                { 'A', 'S' }
        };

        TwoDimArrayStringChecker checker1 = new TwoDimArrayStringChecker("XMAS", smallGrid);
        Integer count1 = checker1.countAllInstancesOfString();

        // Should handle gracefully
        assertNotNull(count1);

        // Test with different string
        TwoDimArrayStringChecker checker2 = new TwoDimArrayStringChecker("XM", smallGrid);
        Integer count2 = checker2.countAllInstancesOfString();

        // Should find XM horizontally
        assertTrue(count2 >= 1);
    }

    @Test
    public void testCompleteExampleGrid() {
        // Test with the actual example grid from the problem
        char[][] grid = {
                { 'M', 'M', 'M', 'S', 'X', 'X', 'M', 'A', 'S', 'M' },
                { 'M', 'S', 'A', 'M', 'X', 'M', 'S', 'M', 'S', 'A' },
                { 'A', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'M', 'M' },
                { 'M', 'S', 'A', 'M', 'A', 'S', 'M', 'S', 'M', 'X' },
                { 'X', 'M', 'A', 'S', 'A', 'M', 'X', 'A', 'M', 'M' },
                { 'X', 'X', 'A', 'M', 'M', 'X', 'X', 'A', 'M', 'A' },
                { 'S', 'M', 'S', 'M', 'S', 'A', 'S', 'X', 'S', 'S' },
                { 'S', 'A', 'X', 'A', 'M', 'A', 'S', 'A', 'A', 'A' },
                { 'M', 'A', 'M', 'M', 'M', 'X', 'M', 'M', 'M', 'M' },
                { 'M', 'X', 'M', 'X', 'A', 'X', 'M', 'A', 'S', 'X' }
        };

        TwoDimArrayStringChecker checker = new TwoDimArrayStringChecker("XMAS", grid);
        Integer count = checker.countAllInstancesOfString();

        // Based on the example, should find 18 instances
        assertEquals(Integer.valueOf(18), count);
    }
}
