package com.ddrake.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public interface Day {
    void solve();

    default String getInputFileName(boolean example) {
        var className = this.getClass().getSimpleName().toLowerCase();
        if (example) {
            return className + "_example.txt";
        } else {
            return className + ".txt";
        }
    }

    default String getInputFileName() {
        return getInputFileName(false);
    }

    default int getDayNumber() {
        var className = this.getClass().getSimpleName();
        return Integer.parseInt(className.replaceAll("\\D", ""));
    }

    default List<String> loadInput(boolean example) {
        var year = getYear();
        var inputFileName = "";
        if (example) {
            inputFileName = getInputFileName(example);
        } else {
            inputFileName = getInputFileName();
        }
        var inputPath = "/inputs/year" + year + "/" + inputFileName;

        try (InputStream is = getClass().getResourceAsStream(inputPath)) {
            if (is == null) {
                throw new RuntimeException("Input file not found: " + inputPath);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load input file: " + inputPath, e);
        }
    }

    default List<String> loadInput() {
        return loadInput(false);
    }

    default int getYear() {
        // Extract year from package name (e.g., com.ddrake.adventofcode.year2024.days)
        var packageName = this.getClass().getPackage().getName();
        var yearPattern = "year(\\d{4})";
        var matcher = java.util.regex.Pattern.compile(yearPattern).matcher(packageName);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new RuntimeException("Could not determine year from package: " + packageName);
    }
}