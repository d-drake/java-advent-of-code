package com.ddrake.adventofcode;

public class DayRunner {
    private DayRegistry dayRegistry = new DayRegistry();

    public DayRunner() {
        dayRegistry.registerDays();
    }

    public void runDay(int year, int day) {
        var key = dayRegistry.makeKey(year, day);
        var dayImpl = dayRegistry.map.get(key);

        if (dayImpl == null) {
            System.err.println("Day " + day + " for year " + year + " not implemented yet");
            return;
        }

        System.out.println("\n=== Year " + year + " Day " + day + " ===");
        timePart(1, dayImpl::part1);
        timePart(2, dayImpl::part2);
    }

    private void timePart(int partNumber, Runnable partRunner) {
        long startTime = System.nanoTime();
        partRunner.run();
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Part %d execution time: %.3f ms%n", partNumber, duration);
    }

    public void runAllDays(int year) {
        var foundAny = false;

        // Try days 1-25 (Advent of Code day range)
        for (int day = 1; day <= 25; day++) {
            var key = dayRegistry.makeKey(year, day);
            if (dayRegistry.map.containsKey(key)) {
                foundAny = true;
                runDay(year, day);
            }
        }

        if (!foundAny) {
            System.err.println("No days implemented for year " + year);
        }
    }
}