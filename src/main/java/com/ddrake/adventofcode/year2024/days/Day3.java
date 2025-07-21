package com.ddrake.adventofcode.year2024.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ddrake.adventofcode.Day;

public class Day3 implements Day {
    @Override
    public void solve() {
        solveForSolution(false);
    }

    public void solveExample() {
        solveForSolution(true);
    }

    private void solveForSolution(boolean example) {
        List<String> input = new ArrayList<>();
        if (example) {
            input = loadInput(example);
        } else {
            input = loadInput(false);
        }
        var parsedInput = regexParseInput(input);
        var calculatedSum = calculateSumOfMultiplications(parsedInput);
        System.out.println("The sum of all \"real\" multiplications is: " + calculatedSum);
    }

    private List<List<Integer>> regexParseInput(List<String> inputStrings) {
        // fill this list of lists
        List<List<Integer>> listArrays = new ArrayList<>();

        // regex pattern for the desired string pat ~ mul(\d+,\d+)
        Pattern regexPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        // iter through the list of strings
        for (String lineString : inputStrings) {
            // the regex matcher
            Matcher matcher = regexPattern.matcher(lineString);
            // look for all matches
            while (matcher.find()) {
                // select group1 and group2 and parse to int's
                int firstNumber = Integer.parseInt(matcher.group(1));
                int secondNumber = Integer.parseInt(matcher.group(2));
                // add to the main list
                listArrays.add(Arrays.asList(firstNumber, secondNumber));
            }
        }
        // return the main list
        return listArrays;
    }

    private Integer calculateSumOfMultiplications(List<List<Integer>> listArrays) {
        int sum = 0;
        // iterate through the list of lists
        for (List<Integer> listNumbers : listArrays) {
            // multiply the numbers, then add to the sum
            sum += (listNumbers.get(0) * listNumbers.get(1));
        }
        return sum;
    }
}
