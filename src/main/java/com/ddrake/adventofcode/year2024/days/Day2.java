// Day 1: Red-Nosed Reports
// 
// https://adventofcode.com/2024/day/2
// 
// input format ~ 
// :: lines of whitespace-separated integers 
// :: inconsistent number of ints per line
// 
// Part 1: ~Find N of "safe" levels;
//      - ints must be all increasing or decreasing
//      - ints may only differ by 1-3
// Part 2: ~Find N of "safe" levels;
//      - same as above;
//      ++ if removing a single level from an unsafe report would make it safe, the report counts as safe

package com.ddrake.adventofcode.year2024.days;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Predicate;

import com.ddrake.adventofcode.Day;

public class Day2 extends Day {
    List<List<Integer>> listNumArrays = new ArrayList<>();
    int resultPartOne;
    int resultPartTwo;

    private void setResultPartOne(int resultPartOne) {
        this.resultPartOne = resultPartOne;
    }

    private void setResultPartTwo(int resultPartTwo) {
        this.resultPartTwo = resultPartTwo;
    }

    // constructors
    public Day2(boolean example) {
        super(example);
        readInputAssignToList();
    }

    public Day2() {
        this(false);
    }

    // part methods
    @Override
    public void part1() {
        List<List<Integer>> safeReports = filterListNumArrays(listNumArrays,
                row -> isRuleSafe(row, 1, 3));
        setResultPartOne(safeReports.size());
        System.out.println("The number of safe reports is: " + resultPartOne);
    }

    @Override
    public void part2() {
        List<List<Integer>> safeReports = filterListNumArrays(listNumArrays,
                row -> isRuleSafeWithOneRemoval(row, 1, 3));
        setResultPartTwo(safeReports.size());
        System.out.println("The number of safe reports is: " + resultPartTwo);
    }

    private void readInputAssignToList() {
        // get input stream
        var inputStream = loadInputStreamLineByLine(linePattern, intArrayMapper);
        inputStream.forEach(intArray -> {
            listNumArrays.add(intArray);
        });
    }

    // pattern ~ a digit followed by whitespace-then-digit for 0+ instances
    private static Pattern linePattern = Pattern.compile("(\\d+)(\\s+\\d+)*");
    //
    private static Function<Matcher, List<Integer>> intArrayMapper = matcher -> {
        String fullLine = matcher.group(0);
        return parseLine(fullLine);
    };

    private static List<Integer> parseLine(String line) {
        String[] tokens = line.trim().split("\\s+");
        List<Integer> numbers = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            numbers.add(Integer.parseInt(token));
        }
        return numbers;
    }

    private List<List<Integer>> filterListNumArrays(List<List<Integer>> listNumArrays,
            Predicate<List<Integer>> safetyCheck) {
        List<List<Integer>> listToFilter = listNumArrays.stream()
                .map(ArrayList::new)
                .collect(java.util.stream.Collectors.toList());

        Iterator<List<Integer>> iterator = listToFilter.iterator();

        while (iterator.hasNext()) {
            List<Integer> row = iterator.next();
            if (!safetyCheck.test(row)) {
                iterator.remove();
            }
        }

        return listToFilter;
    }

    // private static boolean isValid(List<Integer> row) {
    // int size = row.size();
    // if (size < 2)
    // return true; // trivially valid

    // if (isRuleSafe(row, -1))
    // return true; // check full list first

    // // Try removing one element at a time
    // for (int skip = 0; skip < size; skip++) {
    // if (isRuleSafe(row, skip))
    // return true;
    // }

    // return false;
    // }

    private static boolean isRuleSafe(List<Integer> row, int minDiff, int maxDiff) {
        int size = row.size();
        if (size < 2)
            return true; // trivially valid

        int first = row.get(0);
        int second = row.get(1);
        int direction = Integer.compare(second, first); // number > 1, number < 1, or 0

        if (direction == 0)
            return false; // no flat sequences

        for (int i = 1; i < size; i++) {
            int prev = row.get(i - 1);
            int curr = row.get(i);
            int diff = curr - prev;

            boolean rule1Check = Integer.compare(diff, 0) != direction;
            boolean rule2Check = Math.abs(diff) < minDiff || Math.abs(diff) > maxDiff;

            // if (!partTwoDampener) {
            // rule 1 check
            if (rule1Check)
                return false; // not monotonic
            // rule 2 check
            if (rule2Check)
                return false; // invalid diff
            // } else {
            // if (rule1Check || rule2Check)
            // return false;
            // }

        }

        return true;
    }

    private static boolean isRuleSafeWithOneRemoval(List<Integer> row, int minDiff, int maxDiff) {
        if (row.size() <= 2) {
            return true; // Removing one element leaves a trivially safe list
        }

        // Try removing each element one by one (by its index)
        for (int removedIndex = 0; removedIndex < row.size(); removedIndex++) {
            // Check if the list is safe with the element at 'removedIndex' virtually
            // removed.
            if (isSafeWithIndexRemoved(row, removedIndex, minDiff, maxDiff)) {
                return true; // Found a valid removal, no need to check further.
            }
        }

        return false; // No single removal could make the list safe.
    }

    private static boolean isSafeWithIndexRemoved(List<Integer> row, int removedIndex, int minDiff, int maxDiff) {
        Integer prev = null;
        int direction = 0;

        for (int i = 0; i < row.size(); i++) {
            if (i == removedIndex) {
                continue; // Simulate the removal by skipping this element.
            }

            int current = row.get(i);

            if (prev == null) {
                // This is the first element of our simulated list.
                prev = current;
                continue;
            }

            // We now have a 'prev' and 'current' to compare.
            int diff = current - prev;

            if (direction == 0) {
                // This is the first comparison, establish the required direction.
                direction = Integer.compare(diff, 0);
                if (direction == 0)
                    return false; // No flat sequences allowed.
            } else {
                // Check if the direction is consistent.
                if (Integer.compare(diff, 0) != direction)
                    return false;
            }

            // Check if the difference is within the allowed bounds.
            if (Math.abs(diff) < minDiff || Math.abs(diff) > maxDiff) {
                return false;
            }

            // The current element becomes the previous for the next iteration.
            prev = current;
        }

        return true; // All checks passed for this simulated removal.
    }

}
