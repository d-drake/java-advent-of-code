package com.ddrake.adventofcode.year2024.days;

import org.junit.Test;

// import static org.junit.Assert.assertEquals;

// import java.util.List;

import org.junit.Before;

public class Day3Test {
    private Day3 day3;
    private Day3 exampleDay3;

    @Before
    public void setUp() {
        day3 = new Day3();
        exampleDay3 = new Day3(true);
    }

    @Test
    public void testLoadInput() {
        // quick sanity check
        day3.part1();
        System.out.println(day3.listIntPairs);
    }

    @Test
    public void testPart1() {
        exampleDay3.part1();
    }

    @Test
    public void testPart2() {
        exampleDay3.part2();
        // day3.part2();
    }
}