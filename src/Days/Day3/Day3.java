package Days.Day3;

import Days.Day;
import Days.Day3.models.Coordinate;
import Days.Day3.models.PartNumber;
import Days.Util;

import java.util.*;

import static java.lang.Math.*;

public class Day3 extends Day {

    public Day3() {
        super(3);
    }

    private static String SYMBOLS = "*#+$";
    private static String DIGITS = "0123456789";

    @Override
    public void exec() {
        List<String> schematic = Util.readFile(3);

        // Get part numbers
        List<PartNumber> partNumbers = getPartNumbers(schematic);

        part1(schematic, partNumbers);
        part2(schematic, partNumbers);
    }

    private void part1(List<String> schematic, List<PartNumber> partNumbers) {
        printStatementPart1();

        // sum
        int sum = partNumbers.stream()
                .filter(partNumber -> partNumber.isAdyacentToSymbol(schematic))
                .map(PartNumber::getNumber)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum of all part numbers: " + sum);
    }

    private void part2(List<String> schematic, List<PartNumber> partNumbers) {
        printStatementPart2();
        Map<Coordinate, Set<PartNumber>> symbolsAdyacent = new HashMap<>();
        for(PartNumber partNumber : partNumbers){
            for(Coordinate symbolCoordinate : partNumber.getAdyacentAsterisks(schematic)){
                // get
                Set<PartNumber> adyacentPartNumbers = symbolsAdyacent.getOrDefault(symbolCoordinate, new HashSet<>());
                adyacentPartNumbers.add(partNumber);
                // update
                symbolsAdyacent.put(symbolCoordinate, adyacentPartNumbers);
            }
        }
        int gearSum = symbolsAdyacent.keySet().stream()
                .filter(symbol -> symbolsAdyacent.get(symbol).size() == 2) //get gears
                .mapToInt(symbol -> symbolsAdyacent.get(symbol).stream()
                        .map(PartNumber::getNumber)
                        .reduce(1, (a, b) -> a * b)
                ).sum();
        System.out.println("Sum of all gear ratios: " + gearSum);
    }

    private boolean isDigit(char c) {
        return DIGITS.indexOf(c) != -1;
    }

    private List<PartNumber> getPartNumbers(List<String> schematic) {
        List<PartNumber> completeNumbers = new ArrayList<>();
        for (int y = 0; y < schematic.size(); y++) {
            String row = schematic.get(y);

            StringBuilder sb = new StringBuilder();
            Set<Coordinate> coordinates = new HashSet<>();
            for (int x = 0; x < row.length(); x++) {
                char c = row.charAt(x);
                Coordinate currentCoordinate = new Coordinate(x, y);
                if (isDigit(c)) {
                    coordinates.add(currentCoordinate);
                    sb.append(c);
                } else { //symbol or .
                    if (!sb.toString().isEmpty()) {
                        completeNumbers.add(PartNumber.builder()
                                .coordinates(coordinates)
                                .number(Integer.parseInt(sb.toString()))
                                .build());
                    }
                    sb = new StringBuilder();
                    coordinates = new HashSet<>();
                }
            }
            if (!sb.toString().isEmpty()) {
                completeNumbers.add(PartNumber.builder()
                        .coordinates(coordinates)
                        .number(Integer.parseInt(sb.toString()))
                        .build());
            }
        }
        return completeNumbers;
    }
}
