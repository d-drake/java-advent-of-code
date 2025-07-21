package com.ddrake.adventofcode;

import java.util.HashMap;
import java.util.Map;

public class DayRegistry {
    public final Map<String, Day> map = new HashMap<>();

    public void registerDays() {
        // Register 2024 days
        registerDay(2024, 1, new com.ddrake.adventofcode.year2024.days.Day1());
        registerDay(2024, 2, new com.ddrake.adventofcode.year2024.days.Day2());
        // Add more days as they're implemented
    }

    private void registerDay(int year, int day, Day dayImpl) {
        map.put(makeKey(year, day), dayImpl);
    }

    public String makeKey(int year, int day) {
        return year + "-" + day;
    }
}
