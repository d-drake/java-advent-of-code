package com.ddrake.adventofcode;

import java.util.HashMap;
import java.util.Map;

public class DayRunner {
    private final Map<String, Day> dayRegistry = new HashMap<>();
    
    public DayRunner() {
        registerDays();
    }
    
    private void registerDays() {
        // Register 2024 days
        registerDay(2024, 1, new com.ddrake.adventofcode.year2024.days.Day1());
        // Add more days as they're implemented
    }
    
    private void registerDay(int year, int day, Day dayImpl) {
        dayRegistry.put(makeKey(year, day), dayImpl);
    }
    
    public void runDay(int year, int day) {
        var key = makeKey(year, day);
        var dayImpl = dayRegistry.get(key);
        
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
            var key = makeKey(year, day);
            if (dayRegistry.containsKey(key)) {
                foundAny = true;
                runDay(year, day);
            }
        }
        
        if (!foundAny) {
            System.err.println("No days implemented for year " + year);
        }
    }
    
    private String makeKey(int year, int day) {
        return year + "-" + day;
    }
}