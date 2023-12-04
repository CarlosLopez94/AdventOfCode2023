package Days.Day1;

import Days.Day;
import Days.Util;

import java.util.*;

public class Day1 extends Day {

    public Day1() {
        super(1);
    }

    @Override
    public void exec() {
        List<String> lines = Util.readFile(1);

        part1(lines, Map.ofEntries(
                Map.entry("1", "1"),
                Map.entry("2", "2"),
                Map.entry("3", "3"),
                Map.entry("4", "4"),
                Map.entry("5", "5"),
                Map.entry("6", "6"),
                Map.entry("7", "7"),
                Map.entry("8", "8"),
                Map.entry("9", "9")));
        part2(lines, Map.ofEntries(
                Map.entry("1", "1"),
                Map.entry("2", "2"),
                Map.entry("3", "3"),
                Map.entry("4", "4"),
                Map.entry("5", "5"),
                Map.entry("6", "6"),
                Map.entry("7", "7"),
                Map.entry("8", "8"),
                Map.entry("9", "9"),
                Map.entry("one", "1"),
                Map.entry("two", "2"),
                Map.entry("three", "3"),
                Map.entry("four", "4"),
                Map.entry("five", "5"),
                Map.entry("six", "6"),
                Map.entry("seven", "7"),
                Map.entry("eight", "8"),
                Map.entry("nine", "9")));
    }

    private void part1(List<String> documents, Map<String, String> digits) {
        printStatementPart1();

        int calibrationSum = getCalibrationSum(documents, digits);
        System.out.println("The calibration sum is: " + calibrationSum);
    }
    private void part2(List<String> documents, Map<String, String> digits) {
        printStatementPart2();

        int calibrationSum = getCalibrationSum(documents, digits);
        System.out.println("The correct calibration sum is: " + calibrationSum);
    }

    private int getCalibrationSum(List<String> documents, Map<String, String> digits) {
        List<Integer> calibrationValues = new ArrayList<>();
        for (String document : documents) {
            String firstDigit = getFirstDigit(digits, document);
            String lastDigit = getLastDigit(digits, document);
            calibrationValues.add(Integer.valueOf(firstDigit + lastDigit));
        }
        return calibrationValues.stream().mapToInt(Integer::intValue).sum();
    }
    private String getFirstDigit(Map<String, String> digits, String str) {
        StringBuilder aux = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            aux.append(c);
            for (String digit : digits.keySet()) {
                if (aux.toString().contains(digit)) {
                    return digits.get(digit);
                }
            }
        }
        return null;
    }
    private String getLastDigit(Map<String, String> digits, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            sb.insert(0, c);
            for (String digit : digits.keySet()) {
                if (sb.toString().contains(digit)) {
                    return digits.get(digit);
                }
            }
        }
        return null;
    }

}
