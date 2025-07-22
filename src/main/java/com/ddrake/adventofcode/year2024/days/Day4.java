// Word Search for all instances of "XMAS"
// :: allowed directions: diagonal, vertical, written backwords, or even overlapping words
// --> How many instances of "XMAS"?

package com.ddrake.adventofcode.year2024.days;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ddrake.adventofcode.Day;

public class Day4 implements Day {
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
        var twoDimArray = convertInputTo2DArray(input);

        // check output
        // for (char[] line : twoDimArray) {
        // System.out.println(Arrays.toString(line));
        // }

        int nInstancesXMAS = new TwoDimArrayStringChecker("XMAS", twoDimArray).countAllInstancesOfString();
        System.out.println("Total number of \"XMAS\" instances: " + nInstancesXMAS);

        // System.out.println(twoDimArray[0][0]);
        // System.out.println(twoDimArray[0][3]);
        // System.out.println(twoDimArray[3][0]);
    }

    private char[][] convertInputTo2DArray(List<String> listStrings) {
        // init 2D char array
        char[][] twoDimArray = new char[listStrings.size()][];
        // iterate through list of strings
        for (int i = 0; i < listStrings.size(); i++) {
            // convert each line to a character array
            twoDimArray[i] = listStrings.get(i).toCharArray();
        }
        return twoDimArray;
    }

}

class TwoDimArrayStringChecker {
    private String targetString;
    private char[][] twoDimArray;
    private int heightTwoDimArray;
    private int widthTwoDimArray;
    // char[][] indexing ---> [row][col]

    public TwoDimArrayStringChecker(String targetString, char[][] twoDimArray) {
        this.targetString = targetString;
        this.twoDimArray = twoDimArray;
        this.heightTwoDimArray = twoDimArray.length;
        this.widthTwoDimArray = twoDimArray[0].length;
    }

    public Integer countAllInstancesOfString() {
        // init number of found instances of string
        int nInstances = 0;
        // establish col & row index bounds
        int colIndexMaxForwardsCheck = widthTwoDimArray - targetString.length();
        int colIndexMinBackwardsCheck = targetString.length() - 1;
        int rowIndexMaxDownCheck = heightTwoDimArray - targetString.length();
        int rowIndexMinUpCheck = targetString.length() - 1;

        // start letter of targetString
        char startLetter = targetString.charAt(0);
        // iter through all rows
        for (int row = 0; row < heightTwoDimArray; row++) {
            // iter through all cols
            for (int col = 0; col < widthTwoDimArray; col++) {
                // this coord
                Coordinate coord = new Coordinate(row, col);
                // this letter
                char thisLetter = twoDimArray[coord.row][coord.col];
                // check if thisLetter is the startLetter
                if (thisLetter != startLetter) {
                    continue;
                }

                // check horizontal forwards
                if (coord.col <= colIndexMaxForwardsCheck) {
                    boolean wordFound = checkHorizontal(DirectionHorizontal.FORWARD, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check horizontal backwards
                if (coord.col >= colIndexMinBackwardsCheck) {
                    boolean wordFound = checkHorizontal(DirectionHorizontal.BACKWARD, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check vertical upwards
                if (coord.row >= rowIndexMinUpCheck) {
                    boolean wordFound = checkVertical(DirectionVertical.UPWARD, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check vertical downwards
                if (coord.row <= rowIndexMaxDownCheck) {
                    boolean wordFound = checkVertical(DirectionVertical.DOWNWARD, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check diagonal up-right
                if (coord.col <= colIndexMaxForwardsCheck && coord.row >= rowIndexMinUpCheck) {
                    boolean wordFound = checkDiagonal(DirectionDiagonal.UP_RIGHT, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check diagonal up-left
                if (coord.col >= colIndexMinBackwardsCheck && coord.row >= rowIndexMinUpCheck) {
                    boolean wordFound = checkDiagonal(DirectionDiagonal.UP_LEFT, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check diagonal down-right
                if (coord.col <= colIndexMaxForwardsCheck && coord.row <= rowIndexMaxDownCheck) {
                    boolean wordFound = checkDiagonal(DirectionDiagonal.DOWN_RIGHT, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

                // check diagonal down-left
                if (coord.col >= colIndexMinBackwardsCheck && coord.row <= rowIndexMaxDownCheck) {
                    boolean wordFound = checkDiagonal(DirectionDiagonal.DOWN_LEFT, coord);
                    if (wordFound) {
                        nInstances += 1;
                    }
                }

            }

        }

        return nInstances;
    }

    private record Coordinate(int row, int col) {
    }

    private enum DirectionHorizontal {
        FORWARD, BACKWARD
    }

    private enum DirectionVertical {
        UPWARD, DOWNWARD
    }

    private enum DirectionDiagonal {
        UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT
    }

    private boolean checkHorizontal(DirectionHorizontal direction, Coordinate coord) {
        Objects.requireNonNull(direction, "Direction cannot be null");
        Objects.requireNonNull(coord, "Coordinate cannot be null");
        //
        return switch (direction) {
            case FORWARD -> {
                // see bounds check from caller
                if (twoDimArray[coord.row][coord.col + 1] == 'M'
                        && twoDimArray[coord.row][coord.col + 2] == 'A'
                        && twoDimArray[coord.row][coord.col + 3] == 'S') {
                    yield true;
                }
                yield false;
            }

            case BACKWARD -> {
                // see bounds check from caller
                if (twoDimArray[coord.row][coord.col - 1] == 'M'
                        && twoDimArray[coord.row][coord.col - 2] == 'A'
                        && twoDimArray[coord.row][coord.col - 3] == 'S') {
                    yield true;
                }
                yield false;
            }

            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        };
    }

    private boolean checkVertical(DirectionVertical direction, Coordinate coord) {
        Objects.requireNonNull(direction, "Direction cannot be null");
        Objects.requireNonNull(coord, "Coordinate cannot be null");
        // switch logic
        return switch (direction) {
            case DOWNWARD -> {
                // see bounds check from caller
                if (twoDimArray[coord.row + 1][coord.col] == 'M'
                        && twoDimArray[coord.row + 2][coord.col] == 'A'
                        && twoDimArray[coord.row + 3][coord.col] == 'S') {
                    yield true;
                }
                yield false;
            }

            case UPWARD -> {
                // see bounds check from caller
                if (twoDimArray[coord.row - 1][coord.col] == 'M'
                        && twoDimArray[coord.row - 2][coord.col] == 'A'
                        && twoDimArray[coord.row - 3][coord.col] == 'S') {
                    yield true;
                }
                yield false;
            }

            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        };
    }

    private boolean checkDiagonal(DirectionDiagonal direction, Coordinate coord) {
        Objects.requireNonNull(direction, "Direction cannot be null");
        Objects.requireNonNull(coord, "Coordinate cannot be null");
        // switch logic
        return switch (direction) {
            case UP_RIGHT -> {
                // see bounds check from caller
                if (twoDimArray[coord.row - 1][coord.col + 1] == 'M'
                        && twoDimArray[coord.row - 2][coord.col + 2] == 'A'
                        && twoDimArray[coord.row - 3][coord.col + 3] == 'S') {
                    yield true;
                }
                yield false;
            }

            case UP_LEFT -> {
                // see bounds check from caller
                if (twoDimArray[coord.row - 1][coord.col - 1] == 'M'
                        && twoDimArray[coord.row - 2][coord.col - 2] == 'A'
                        && twoDimArray[coord.row - 3][coord.col - 3] == 'S') {
                    yield true;
                }
                yield false;
            }

            case DOWN_RIGHT -> {
                // see bounds check from caller
                if (twoDimArray[coord.row + 1][coord.col + 1] == 'M'
                        && twoDimArray[coord.row + 2][coord.col + 2] == 'A'
                        && twoDimArray[coord.row + 3][coord.col + 3] == 'S') {
                    yield true;
                }
                yield false;
            }

            case DOWN_LEFT -> {
                // see bounds check from caller
                if (twoDimArray[coord.row + 1][coord.col - 1] == 'M'
                        && twoDimArray[coord.row + 2][coord.col - 2] == 'A'
                        && twoDimArray[coord.row + 3][coord.col - 3] == 'S') {
                    yield true;
                }
                yield false;
            }

            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        };
    }

}
