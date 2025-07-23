package com.ddrake.adventofcode.year2024.days;

import org.junit.Before;
import org.junit.Test;

public class Day4Test {
    // private Day4 day4;
    private Day4 exampleDay4;

    @Before
    public void setUp() {
        // day4 = new Day4();
        exampleDay4 = new Day4(true);
    }

    @Test
    public void testLoadInput() {
        // for (char[] charArr : day4.twoDimCharArray) {
        // System.out.println(charArr);
        // }
        for (char[] charArr : exampleDay4.twoDimCharArray) {
            System.out.println(charArr);
        }
    }

    @Test
    public void testPart1() {
        exampleDay4.part1();
    }

    @Test
    public void testPart2() {
        exampleDay4.part2();
        // day3.part2();
    }

}