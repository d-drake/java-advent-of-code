package com.ddrake.adventofcode.year2024.days;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

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

    @Test
    public void testParseAndSortInput() throws Exception {
        // Use reflection to test private method
        Method method = Day1.class.getDeclaredMethod("parseAndSortInput", List.class);
        method.setAccessible(true);

        List<String> input = Arrays.asList("3   4", "4   3", "2   5", "1   3", "3   9", "3   3");
        TwoLists result = (TwoLists) method.invoke(day1, input);

        // Check that lists are parsed correctly
        assertEquals(6, result.listA().size());
        assertEquals(6, result.listB().size());

        // Check that lists are sorted
        assertEquals(Arrays.asList(1, 2, 3, 3, 3, 4), result.listA());
        assertEquals(Arrays.asList(3, 3, 3, 4, 5, 9), result.listB());
    }

    @Test
    public void testParseAndSortInputWithInvalidFormat() throws Exception {
        Method method = Day1.class.getDeclaredMethod("parseAndSortInput", List.class);
        method.setAccessible(true);

        // Test with invalid input
        List<String> input = Arrays.asList("3   4", "invalid", "2   5   6", "1   3");
        TwoLists result = (TwoLists) method.invoke(day1, input);

        // Should only parse valid lines
        assertEquals(2, result.listA().size());
        assertEquals(2, result.listB().size());
    }

    @Test
    public void testSumArrayDifferences() throws Exception {
        Method method = Day1.class.getDeclaredMethod("sumArrayDifferences", List.class);
        method.setAccessible(true);

        List<Integer> differences = Arrays.asList(2, 1, 0, 1, 2, 5);
        Integer sum = (Integer) method.invoke(day1, differences);

        assertEquals(Integer.valueOf(11), sum);
    }

    @Test
    public void testEmptyInput() throws Exception {
        Method parseMethod = Day1.class.getDeclaredMethod("parseAndSortInput", List.class);
        parseMethod.setAccessible(true);

        List<String> emptyInput = new ArrayList<>();
        TwoLists result = (TwoLists) parseMethod.invoke(day1, emptyInput);

        assertEquals(0, result.listA().size());
        assertEquals(0, result.listB().size());
    }

    @Test
    public void testSingleElementInput() throws Exception {
        Method parseMethod = Day1.class.getDeclaredMethod("parseAndSortInput", List.class);
        parseMethod.setAccessible(true);

        List<String> singleInput = Arrays.asList("42   100");
        TwoLists result = (TwoLists) parseMethod.invoke(day1, singleInput);

        assertEquals(1, result.listA().size());
        assertEquals(1, result.listB().size());
        assertEquals(Integer.valueOf(42), result.listA().get(0));
        assertEquals(Integer.valueOf(100), result.listB().get(0));
    }
}