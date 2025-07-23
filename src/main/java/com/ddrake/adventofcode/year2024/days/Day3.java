// Day 3: Mull It Over
// 
// https://adventofcode.com/2024/day/3
// 
// input format ~ 
// :: lines of junk strings (all sorts of string content)
// 
// Part 1: ~Find sum of multiplcations found in strings;
//      - multiplication terms ==> sections of string with following pattern ~ "mul(\d+,\d+)"
//      - multiply \d+ * \d*
// Part 2: ~Find sum of multiplcations found in strings;
//      - same as above;
//      - starting "mul(\d+,\d+)" terms acceptable until "don't()" encountered
//      - ok to continue accepting if a subsequent "do" encountered

package com.ddrake.adventofcode.year2024.days;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ddrake.adventofcode.Day;

record IntPair(int first, int second) {
}

public class Day3 extends Day {
    List<IntPair> listIntPairs = new ArrayList<>();
    int resultPartOne;
    int resultPartTwo;

    private void setResultPartOne(int resultPartOne) {
        this.resultPartOne = resultPartOne;
    }

    private void setResultPartTwo(int resultPartTwo) {
        this.resultPartTwo = resultPartTwo;
    }

    // constructors
    public Day3(boolean example) {
        super(example);
    }

    public Day3() {
        this(false);
    }

    // part methods
    @Override
    public void part1() {
        // read the input & fill listIntPairs class parameter
        readInputAssignToList(PartOption.PART_ONE);
        // run the calculation to assign resultPartOne
        setResultPartOne(calculateSumOfMultiplicationTerms());
        System.out.println("The sum of multiplication terms is: " + resultPartOne);
    }

    @Override
    public void part2() {
        // clear the listIntPairs first
        listIntPairs.clear();
        // read the input & fill listIntPairs class parameter using modified
        // regex line scan (with segmentPatternPart2) given new rules
        readInputAssignToList(PartOption.PART_TWO);
        setResultPartTwo(calculateSumOfMultiplicationTerms());
        System.out.println("The sum of multiplication terms is: " + resultPartTwo);
    }

    // arg options for next method
    private enum PartOption {
        PART_ONE, PART_TWO
    }

    // read the input a bit differently depending on part
    private void readInputAssignToList(PartOption option) {
        Function<String, List<IntPair>> contentProcessor;

        switch (option) {
            case PART_ONE -> {
                contentProcessor = PartOneMulExtractor::parseContentExtractIntPairs;
            }

            case PART_TWO -> {
                contentProcessor = PartTwoMulExtractor::parseContentExtractIntPairs;
            }

            default -> throw new RuntimeException("Invalid PartOption: " + option);
        }

        var intPairs = loadInputFullContent(contentProcessor);
        listIntPairs.addAll(intPairs);
    }

    // calculations
    // part 1 calc
    private int calculateSumOfMultiplicationTerms() {
        int sum = 0;
        for (IntPair intPair : listIntPairs) {
            sum += intPair.first() * intPair.second();
        }
        return sum;
    }
}

class PartOneMulExtractor {

    public static Pattern mulSegmentPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    // content parser for entire file
    public static List<IntPair> parseContentExtractIntPairs(String content) {
        Matcher patternMatcher = mulSegmentPattern.matcher(content);
        List<IntPair> listIntPairs = new ArrayList<IntPair>();
        while (patternMatcher.find()) {
            int first = Integer.parseInt(patternMatcher.group(1));
            int second = Integer.parseInt(patternMatcher.group(2));
            listIntPairs.add(new IntPair(first, second));
        }
        return listIntPairs;
    }
}

class PartTwoMulExtractor {

    // content parser for entire file
    public static List<IntPair> parseContentExtractIntPairs(String content) {

        // regex pattern matches *everything* after do() or start of string
        // --> recall ?: is non-capturing group so start of line or do() are
        // not-captured, but following content is captured
        // --> && ?= is a positive lookahead assertion; either don't() or end of string
        Pattern blockPattern = Pattern.compile("(?:^|do\\(\\))(.*?)(?=don't\\(\\)|$)", Pattern.DOTALL);
        // then this will look for the mul(\d+,\d+) patterns captured from the .* above
        // Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        // fill this list
        List<IntPair> listIntPairs = new ArrayList<>();
        // regex sequence...
        // match allowed block strings
        Matcher blockMatcher = blockPattern.matcher(content);
        while (blockMatcher.find()) {
            String block = blockMatcher.group(1);
            Matcher mulMatcher = PartOneMulExtractor.mulSegmentPattern.matcher(block);
            while (mulMatcher.find()) {
                int first = Integer.parseInt(mulMatcher.group(1));
                int second = Integer.parseInt(mulMatcher.group(2));
                listIntPairs.add(new IntPair(first, second));
            }
        }
        return listIntPairs;
    }
}