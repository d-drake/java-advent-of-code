package com.ddrake.adventofcode;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        try {
            switch (args.length) {
                case 1 -> {
                    int year = Integer.parseInt(args[0]);
                    runAllDaysForYear(year);
                }
                case 2 -> {
                    int year = Integer.parseInt(args[0]);
                    int day = Integer.parseInt(args[1]);
                    runSpecificDay(year, day);
                }
                default -> {
                    printUsage();
                    System.exit(1);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Year and day must be valid integers");
            printUsage();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  Run all days:     java -jar advent.jar <year>");
        System.out.println("  Run specific day: java -jar advent.jar <year> <day>");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java -jar advent.jar 2024        # Run all days for 2024");
        System.out.println("  java -jar advent.jar 2024 1      # Run day 1 of 2024");
    }

    private static void runAllDaysForYear(int year) {
        System.out.println("Running all days for year " + year);
        DayRunner runner = new DayRunner();
        runner.runAllDays(year);
    }

    private static void runSpecificDay(int year, int day) {
        System.out.println("Running year " + year + ", day " + day);
        DayRunner runner = new DayRunner();
        runner.runDay(year, day);
    }
}
