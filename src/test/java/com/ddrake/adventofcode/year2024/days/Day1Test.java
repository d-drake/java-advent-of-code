package com.ddrake.adventofcode.year2024.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
// import static org.junit.Assert.*;

public class Day1Test {
    private Day1 day1;
    private Day1 exampleDay1;

    @Before
    public void setUp() {
        day1 = new Day1();
        exampleDay1 = new Day1(true);
    }

    @Test
    public void testLoadInput() {
        // quick sanity check
        System.out.println(exampleDay1.listA);
        System.out.println(exampleDay1.listB);
        // knowns
        List<Integer> knownListA = List.of(1, 2, 3, 3, 3, 4);
        List<Integer> knownListB = List.of(3, 3, 3, 4, 5, 9);
        // assertions
        assertEquals(knownListA, exampleDay1.listA);
        assertEquals(knownListB, exampleDay1.listB);
    }

    @Test
    public void testPart1() {
        exampleDay1.part1();
        day1.part1();
    }

    @Test
    public void testPart2() {
        exampleDay1.part2();
    }

}