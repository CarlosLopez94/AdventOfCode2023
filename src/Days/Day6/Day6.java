package Days.Day6;

import Days.Day;
import Days.Day6.model.Race;
import Days.Util;

import java.math.BigInteger;
import java.util.*;

public class Day6 extends Day {

    public Day6() {
        super(6);
    }

    @Override
    public void exec() {
        List<String> input = Util.readFile(6);

        part1(input);
        part2(input);
    }

    private void part1(List<String> input) {
        printStatementPart1();

        List<Race> races = parseRaces(input);

        int multiplication = races.stream()
                .map(Race::getHoldTimesThatBeatsRecord)
                .map(Set::size)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Multiplication: " + multiplication);
    }

    private void part2(List<String> input) {
        printStatementPart2();

        BigInteger time = new BigInteger(String.join("",
                input.get(0).replaceAll("Time:", "")
                .trim()
                .replaceAll("\\s+",",")
                .split(",")));
        BigInteger distance = new BigInteger(String.join("",
                input.get(1).replaceAll("Distance:", "")
                .trim()
                .replaceAll("\\s+", ",")
                .split(",")));
        Race race = Race.builder()
                .time(time)
                .distance(distance)
                .build();

        System.out.println("Multiplication: " + race.getHoldTimesThatBeatsRecord().size());
    }

    private List<Race> parseRaces(List<String> input) {
        List<Race> races = new ArrayList<>();

        String[] times = input.get(0).replaceAll("Time:", "")
                .trim()
                .replaceAll("\\s+",",")
                .split(",");
        String[] distances = input.get(1).replaceAll("Distance:", "")
                .trim()
                .replaceAll("\\s+", ",")
                .split(",");

        for (int i = 0; i < times.length; i++) {
            races.add(Race.builder()
                    .time(new BigInteger(times[i]))
                    .distance(new BigInteger(distances[i]))
                    .build());
        }
        return races;
    }
}
