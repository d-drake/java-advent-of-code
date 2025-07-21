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
        dayImpl.solve();
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