// Day 1: Historian Hysteria
// 
// https://adventofcode.com/2024/day/1
// 
// input format ~ lines where there are 2 integers serpated by whitespace
// 
// Part 1: ~Find sum distance between two provided lists
// Part 2: ~Find similiarty score between the same two lists

package com.ddrake.adventofcode.year2024.days;

import com.ddrake.adventofcode.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

record NumberPair(int firstNumber, int secondNumber) {
}

public class Day1 extends Day {
    List<Integer> listA;
    List<Integer> listB;
    int resultPartOne;
    int resultPartTwo;

    // setters
    private void setListA(List<Integer> listA) {
        this.listA = listA;
    }

    private void setListB(List<Integer> listB) {
        this.listB = listB;
    }

    private void setResultPartOne(int resultPartOne) {
        this.resultPartOne = resultPartOne;
    }

    private void setResultPartTwo(int resultPartTwo) {
        this.resultPartTwo = resultPartTwo;
    }

    // constructors
    public Day1(boolean example) {
        super(example);
        readInputAssignToLists();
    }

    public Day1() {
        this(false);
    }

    // part methods
    @Override
    public void part1() {
        // calc sum distance
        calculateTotalDistance();
        System.out.println("The total distance between the two lists is: " + resultPartOne);
    }

    @Override
    public void part2() {
        // calc similiarty score
        calcSimiliarityScore();
        System.out.println("The similarity score is: " + resultPartTwo);
    }

    // calc methods
    protected void readInputAssignToLists() {
        // get the input stream
        var inputStream = loadInputStreamLineByLine(linePattern, numberPairMapper);
        // init lists to fill
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        // use the stream to assign the numbers to separate lists
        inputStream.forEach(numberPair -> {
            listA.add(numberPair.firstNumber());
            listB.add(numberPair.secondNumber());
        });
        // sort the lists
        listA.sort(null);
        listB.sort(null);
        // assign lists to object
        setListA(listA);
        setListB(listB);
    }

    private Pattern linePattern = Pattern.compile("(\\d+)\\s+(\\d+)");

    private Function<Matcher, NumberPair> numberPairMapper = matcher -> {
        int firstNumber = Integer.parseInt(matcher.group(1));
        int secondNumber = Integer.parseInt(matcher.group(2));
        return new NumberPair(firstNumber, secondNumber);
    };

    private void calculateTotalDistance() {
        int sum = 0;
        for (int i = 0; i < listA.size(); i++) {
            int distance = Math.abs(listA.get(i) - listB.get(i));
            sum += distance;
        }
        setResultPartOne(sum);
    }

    private void calcSimiliarityScore() {
        // Create a frequency map of listB to count occurrences of each number.
        // This is an O(M) operation, where M is the size of listB.
        Map<Integer, Long> frequencyMapB = listB.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()));

        // Iterate through listA and use the frequency map to calculate the score.
        // This is an O(N) operation, where N is the size of listA.
        // The total complexity is O(N + M)
        long score = listA.stream()
                .mapToLong(numberA -> numberA * frequencyMapB.getOrDefault(numberA, 0L))
                .sum();

        setResultPartTwo((int) score);
    }

}