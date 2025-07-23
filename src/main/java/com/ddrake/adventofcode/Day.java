package com.ddrake.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public abstract class Day {
    // allow example modifer for Day objects
    protected boolean example;

    // constructors
    public Day(boolean example) {
        this.example = example;
    }

    public Day() {
        this(false);
    }

    // abstract part methods
    public abstract void part1();

    public abstract void part2();

    public <T> T loadInputFullContent(Function<String, T> contentProcessor) {
        var inputPath = getInputFilePath();

        try {
            // attempt to setup input stream; looking up input file name
            InputStream iStream = getClass().getResourceAsStream(inputPath);
            if (iStream == null) {
                throw new RuntimeException("Input file not found: " + inputPath);
            }

            // Read entire content at once
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(iStream, StandardCharsets.UTF_8))) {
                StringBuilder contentBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line).append("\n");
                }
                // Remove trailing newline if present
                if (contentBuilder.length() > 0 && contentBuilder.charAt(contentBuilder.length() - 1) == '\n') {
                    contentBuilder.deleteCharAt(contentBuilder.length() - 1);
                }
                String fullContent = contentBuilder.toString();
                return contentProcessor.apply(fullContent);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read input file: " + inputPath, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load input file: " + inputPath, e);
        }
    }

    public <T> Stream<T> loadInputStreamLineByLine(Pattern linePattern, Function<Matcher, T> mapper) {
        var inputPath = getInputFilePath();

        try {
            // attempt to setup input stream; looking up input file name
            InputStream iStream = getClass().getResourceAsStream(inputPath);
            if (iStream == null) {
                throw new RuntimeException("Input file not found: " + inputPath);
            }

            // form the buffered reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream, StandardCharsets.UTF_8));
            // read input lazily, line by line
            return reader.lines()
                    // apply the regex pattern matching logic
                    .map(line -> {
                        Matcher matcher = linePattern.matcher(line);
                        if (matcher.matches()) {
                            // System.out.println(line);
                            return mapper.apply(matcher);
                        } else {
                            throw new IllegalArgumentException("Line does not match pattern: " + line);
                        }
                    })
                    .onClose(() -> {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException("Failed to load input file: " + inputPath, e);
        }
    }

    private String getInputFilePath() {
        var year = getYear();
        var inputFileName = getInputFileName();
        return "/inputs/year" + year + "/" + inputFileName;
    }

    private String getInputFileName() {
        var className = getClass().getSimpleName().toLowerCase();
        if (example) {
            return className + "_example.txt";
        } else {
            return className + ".txt";
        }
    }

    private int getYear() {
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