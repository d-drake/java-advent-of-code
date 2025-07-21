package com.ddrake.adventofcode.year2024.days;

import java.util.ArrayList;
import java.util.List;

import com.ddrake.adventofcode.Day;

public class Day2 implements Day {
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
        var parsedInput = parseInput(input);
        var arrayReports = checkReportSafetyMonotonic(parsedInput);
        arrayReports = checkReportSafetyAdjacentLevels(arrayReports);
        // System.out.println(parsedInput);
        System.out.println("The total number of safe reports is: " + arrayReports.size());
        // var arrayDiffs = getArrayAbsDifferences(parsedInput);
        // var sum = sumArrayDifferences(arrayDiffs);
        // System.out.println("The total distance between left and right distance is: "
        // + sum);
    }

    private List<List<Integer>> parseInput(List<String> inputStrings) {
        // list of list of integers for output
        List<List<Integer>> arrayLists = new ArrayList<>();
        // iterate through the list of lines
        for (String lineString : inputStrings) {
            // fill this subarray
            List<Integer> subArray = new ArrayList<>();
            // split the line string into a list by splitting at spaces
            String[] splitString = lineString.split("\\s");
            // parse the strings to integers
            for (String numberString : splitString) {
                try {
                    int number = Integer.parseInt(numberString);
                    subArray.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Unexpected number format for: " + numberString + "\n" + e.getMessage());
                }
            }
            // append to the main list of this method
            arrayLists.add(subArray);
        }
        return arrayLists;
    }

    private List<List<Integer>> checkReportSafetyMonotonic(List<List<Integer>> inputArrays) {
        List<List<Integer>> cleanedArrayReports = new ArrayList<>();

        // iterate through the list of input arrays
        for (List<Integer> arrayIntegers : inputArrays) {
            boolean isStrictlyMonotonic = ArrayIntegersMonotonicChecker.isStrictlyMonotonic(arrayIntegers);
            // if its strictly monotonic, add it to the cleaned array of reports
            if (isStrictlyMonotonic) {
                cleanedArrayReports.add(arrayIntegers);
            }
        }

        return cleanedArrayReports;
    }

    private List<List<Integer>> checkReportSafetyAdjacentLevels(List<List<Integer>> cleanedInputArrays) {
        List<List<Integer>> cleanedArrayReports = new ArrayList<>();

        // iter through the arrays accepted as strictly monotonic
        for (List<Integer> arrayIntegers : cleanedInputArrays) {
            boolean hasValidAdjacentLevels = ArrayIntegersAdjacencyChecker.hasValidDifferences(
                    arrayIntegers, 1, 3);
            // if it has valid adjacent levels, then add it to the cleaned array
            if (hasValidAdjacentLevels) {
                cleanedArrayReports.add(arrayIntegers);
            }
        }
        return cleanedArrayReports;
    }

}

class ArrayIntegersMonotonicChecker {
    // is strictly monotonic check
    public static boolean isStrictlyMonotonic(List<Integer> arr) {
        if (arr.size() <= 1) {
            return true; // Single element or empty array is considered monotonic
        }

        return isStrictlyIncreasing(arr) || isStrictlyDecreasing(arr);
    }

    private static boolean isStrictlyIncreasing(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) <= arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStrictlyDecreasing(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) >= arr.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}

class ArrayIntegersAdjacencyChecker {
    public static boolean hasValidDifferences(List<Integer> arr, int minDiff, int maxDiff) {
        if (arr.size() <= 1) {
            return true;
        }

        for (int i = 1; i < arr.size(); i++) {
            int diff = Math.abs(arr.get(i) - arr.get(i - 1));
            if (diff < minDiff || diff > maxDiff) {
                return false;
            }
        }
        return true;
    }
}