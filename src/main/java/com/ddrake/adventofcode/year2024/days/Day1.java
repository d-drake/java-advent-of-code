package com.ddrake.adventofcode.year2024.days;

import com.ddrake.adventofcode.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record TwoLists(List<Integer> listA, List<Integer> listB) {
}

public class Day1 implements Day {
    @Override
    public void solve() {
        solveForSolution(false);
    }

    public void solveExample() {
        solveForSolution(true);
    }

    private void solveForSolution(boolean example) {
        // System.out.println("Example: " + example);
        List<String> input = new ArrayList<>();
        if (example) {
            input = loadInput(example);
        } else {
            input = loadInput(false);
        }
        TwoLists parsedInput = parseAndSortInput(input);
        var arrayDiffs = getArrayAbsDifferences(parsedInput);
        var sum = sumArrayDifferences(arrayDiffs);
        System.out.println("The total distance between left and right distance is: " + sum);
    }

    private TwoLists parseAndSortInput(List<String> rawInput) {
        // init output lists
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        // iterate through the raw input
        for (String lineInput : rawInput) {
            // split the string
            String[] splitString = lineInput.split("\\s+");

            // parse the line & convert strings to integers
            if (splitString.length == 2) {
                try {
                    int num1 = Integer.parseInt(splitString[0]);
                    int num2 = Integer.parseInt(splitString[1]);

                    firstList.add(num1);
                    secondList.add(num2);

                } catch (NumberFormatException e) {
                    System.err.println("Error: Invalid number format in string, " + lineInput + ". Full Error Message: "
                            + e.getMessage());
                }
            } else {
                System.err.println("Error: String does not contain exactly two integers, " + lineInput + ".");
            }
        }
        // sort the lists of ints
        Collections.sort(firstList);
        Collections.sort(secondList);
        return new TwoLists(firstList, secondList);
    }

    private List<Integer> getArrayAbsDifferences(TwoLists twoLists) {
        // inst the diffs array
        List<Integer> absoluteDifferences = new ArrayList<>();
        // get the lists
        var firstList = twoLists.listA();
        var secondList = twoLists.listB();
        // check both lists are same length
        if (firstList.size() != secondList.size()) {
            System.err.println("Error: The provided lists are not the same length!");
        }

        // iterate through the lists and calc the abs diff
        for (int i = 0; i < firstList.size(); i++) {
            int num1 = firstList.get(i);
            int num2 = secondList.get(i);
            int absDiff = Math.abs(num1 - num2);
            absoluteDifferences.add(absDiff);
        }
        return absoluteDifferences;
    }

    private Integer sumArrayDifferences(List<Integer> arrayDiffs) {
        int sum = 0;
        // iterate and sum up the array
        for (int number : arrayDiffs) {
            sum += number;
        }
        return sum;
    }
}
