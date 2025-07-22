package com.ddrake.adventofcode.year2024.days;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

public class Day3Test {
    private Day3 day3;

    @Before
    public void setUp() {
        day3 = new Day3();
    }

    @Test
    public void testExampleProblem() {
        // test with the example input
        day3.solveExample();
    }

    @Test
    public void testCalculateSumOfMultiplications() throws Exception {
        Method method = Day3.class.getDeclaredMethod("calculateSumOfMultiplications", List.class);
        method.setAccessible(true);

        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(2, 4), // 8
                Arrays.asList(5, 5), // 25
                Arrays.asList(11, 8), // 88
                Arrays.asList(8, 5) // 40
        );

        Integer result = (Integer) method.invoke(day3, input);

        assertEquals(Integer.valueOf(161), result);
    }

    @Test
    public void testCalculateSumOfMultiplicationsEmpty() throws Exception {
        Method method = Day3.class.getDeclaredMethod("calculateSumOfMultiplications", List.class);
        method.setAccessible(true);

        List<List<Integer>> input = new ArrayList<>();

        Integer result = (Integer) method.invoke(day3, input);

        assertEquals(Integer.valueOf(0), result);
    }

    @Test
    public void testCalculateSumOfMultiplicationsSingle() throws Exception {
        Method method = Day3.class.getDeclaredMethod("calculateSumOfMultiplications", List.class);
        method.setAccessible(true);

        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(10, 20) // 200
        );

        Integer result = (Integer) method.invoke(day3, input);

        assertEquals(Integer.valueOf(200), result);
    }
}
