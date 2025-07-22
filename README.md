# Advent of Code Solutions

My solutions to the [Advent of Code](https://adventofcode.com/) programming puzzles implemented in Java.

## Overview

This repository contains my Java solutions for Advent of Code challenges. The project is structured to support multiple years and provides a clean framework for solving daily puzzles.

## Project Structure

```
advent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ddrake/adventofcode/
│   │   │       ├── Main.java           # Application entry point
│   │   │       ├── Day.java            # Day interface for all solutions
│   │   │       ├── DayRunner.java      # Runner for executing solutions
│   │   │       └── year2024/          # 2024 solutions
│   │   │           └── days/
│   │   │               └── Day1.java   # Day 1 solution
│   │   └── resources/
│   │       └── inputs/
│   │           └── year2024/           # Input files for 2024
│   └── test/
│       └── java/
│           └── com/ddrake/adventofcode/
│               └── year2024/
│                   └── days/
│                       └── Day1Test.java
├── build.gradle
└── settings.gradle
```

## Requirements

- Java 21 or higher
- Gradle 7.0 or higher

## Running Solutions

### Run all solutions for a year
```bash
./gradlew run --args="2024"
```

### Run a specific day
```bash
./gradlew run --args="2024 1"
```

## Testing

### Run all tests
```bash
./gradlew test
```

### Run tests for a specific day
```bash
./gradlew test --tests "com.ddrake.adventofcode.year2024.days.Day1Test"
```

### Run tests with output
```bash
./gradlew test --info
```

## Adding New Solutions

1. Create a new `DayX` class in the appropriate year package
2. Implement the `Day` interface
3. Add your puzzle input to `src/main/resources/inputs/yearYYYY/dayX.txt`
4. Add example input to `src/main/resources/inputs/yearYYYY/dayX_example.txt`
5. Create a corresponding test class

Example structure for a new day:
```java
public class Day2 implements Day {
    @Override
    public void solve() {
        // Your solution here
    }
}
```

## Solutions

### [Year 2024](src/main/java/com/ddrake/adventofcode/year2024/days/)

| Day | Problem | Solution | Status |
|-----|---------|----------|--------|
| [Day 1](https://adventofcode.com/2024/day/1) | Historian Hysteria | [Day1.java](src/main/java/com/ddrake/adventofcode/year2024/days/Day1.java) | ⭐ |
| [Day 2](https://adventofcode.com/2024/day/2) | Red-Nosed Reports | [Day2.java](src/main/java/com/ddrake/adventofcode/year2024/days/Day2.java) | ⭐ |
| [Day 3](https://adventofcode.com/2024/day/3) | Mull It Over | [Day3.java](src/main/java/com/ddrake/adventofcode/year2024/days/Day3.java) | ⭐ |
| [Day 4](https://adventofcode.com/2024/day/4) | Ceres Search | [Day4.java](src/main/java/com/ddrake/adventofcode/year2024/days/Day4.java) | ⭐ |
| Day 5-25 | Coming Soon | - | - |

## Resources

- [Advent of Code](https://adventofcode.com/) - The official website

## License

This project is open source and available under the [MIT License](LICENSE).