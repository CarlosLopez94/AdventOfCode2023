package Days.Day4;

import Days.Day;
import Days.Day3.models.Coordinate;
import Days.Day3.models.PartNumber;
import Days.Day4.models.ScratchCard;
import Days.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 extends Day {

    public Day4() {
        super(4);
    }

    @Override
    public void exec() {
        List<String> input = Util.readFile(4);

        // Get scratchCards
        List<ScratchCard> scratchCards = parsesScratchCards(input);
        part1(scratchCards);
        part2(scratchCards);
    }

    private void part1(List<ScratchCard> scratchCards) {
        printStatementPart1();

        int sumPoints = scratchCards.stream()
                .mapToInt(ScratchCard::getPoints)
                .sum();
        System.out.println("The sum of points is: " + sumPoints);
    }

    private void part2(List<ScratchCard> scratchCards) {
        printStatementPart2();

        int maxId = scratchCards.stream()
                .max(Comparator.comparingInt(ScratchCard::getId))
                .get()
                .getId();

        Map<Integer, Integer> scratchCardCopies = new HashMap<>();
        scratchCards.forEach(sc -> scratchCardCopies.put(sc.getId(), 1));
        for (ScratchCard scratchCard : scratchCards) {
            int copies = scratchCardCopies.get(scratchCard.getId());
            for (int nextId = scratchCard.getId() + 1; nextId <= (scratchCard.getId() + scratchCard.getCardWinningNumbers().size()); nextId++) {
                if (nextId <= maxId) {
                    scratchCardCopies.put(nextId, scratchCardCopies.get(nextId) + copies);
                }
            }
        }
        int totalCardCopies = scratchCardCopies.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Total cards are : " + totalCardCopies);
    }

    private List<ScratchCard> parsesScratchCards(List<String> inputLines) {
        List<ScratchCard> scratchCards = new ArrayList<>();
        for (String line : inputLines) {
            System.out.println(line);
            int id = Integer.parseInt(line.split(": ")[0].replaceAll("Card ", "").trim());
            String[] numberTokens = line.split(": ")[1].split(" \\| ");

            Set<Integer> winningNumbers = Arrays.stream(numberTokens[0]
                            .trim().split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());
            Set<Integer> numbers = Arrays.stream(numberTokens[1]
                            .trim().split(" "))
                    .filter(s -> !s.isEmpty())
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());
            scratchCards.add(ScratchCard.builder()
                    .id(id)
                    .winningNumber(winningNumbers)
                    .numbers(numbers)
                    .build());
        }
        return scratchCards;
    }

}
