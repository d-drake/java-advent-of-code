package com.ddrake.adventofcode.year2024.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;

public class Day2Test {
    // private Day2 day2;
    private Day2 exampleDay2;

    @Before
    public void setUp() {
        // day2 = new Day2();
        exampleDay2 = new Day2(true);
    }

    @Test
    public void testLoadInput() {
        // quick sanity check
        System.out.println(exampleDay2.listNumArrays);
        // sample known
        List<Integer> secondArray = List.of(1, 2, 7, 8, 9);
        // assertion of the second array in the example input
        assertEquals(secondArray, exampleDay2.listNumArrays.get(1));
    }

    @Test
    public void testPart1() {

        exampleDay2.part1();
        // day2.part1();
    }

    @Test
    public void testPart2() {
        exampleDay2.part2();
    }

    @Test
    public void testIntegerCompare() {
        System.out.println(Integer.compare(10, 5)); // 1
        System.out.println(Integer.compare(5, 10)); // -1
        System.out.println(Integer.compare(5, 5)); // 0
    }

}