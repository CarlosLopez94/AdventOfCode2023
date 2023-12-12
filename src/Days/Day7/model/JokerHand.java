package Days.Day7.model;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class JokerHand implements Comparable<Hand> {
    private static String CARDS_ORDER = "J23456789TQKA";
    private BigInteger bid;
    private String cards;
    private final HandType bestHandType;

    public JokerHand(BigInteger bid, String cards) {
        this.bid = bid;
        this.cards = cards;
        this.bestHandType = calculateBestHandType();
    }

    private HandType calculateBestHandType() {
        Map<Character, Long> ocurrences = this.cards.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (ocurrences.size() == 1) {
            return HandType.FIVE_OF_A_KIND;
        } else if (ocurrences.size() == 2 && ocurrences.values().stream().anyMatch(oc -> oc == 4)) {
            return HandType.FOUR_OF_A_KIND;
        } else if (ocurrences.size() == 2 && ocurrences.values().stream().anyMatch(oc -> oc == 3)
                && ocurrences.values().stream().anyMatch(oc -> oc == 2)) {
            return HandType.FULL_HOUSE;
        } else if (ocurrences.size() > 2 && ocurrences.values().stream().anyMatch(oc -> oc == 3)) {
            return HandType.THREE_OF_A_KIND;
        } else if (ocurrences.size() == 3 && ocurrences.values().stream().anyMatch(oc -> oc == 1)
                && ocurrences.values().stream().filter(oc -> oc == 2).toList().size() == 2) {
            return HandType.TWO_PAIR;
        } else if (ocurrences.size() == 4 && ocurrences.values().stream().anyMatch(oc -> oc == 2)) {
            return HandType.ONE_PAIR;
        } else if (ocurrences.size() == 5) {
            return HandType.HIGH_CARD;
        } else {
            System.out.println("NO hand... ");
        }
        System.out.println();
        return null;
    }

    @Override
    public int compareTo(Hand o) {
        int compareHand = Integer.compare(this.bestHandType.order, o.getBestHandType().order);
        if (compareHand != 0) {
            return compareHand;
        } else {
            for (int i = 0; i < this.cards.length(); i++) {
                int firstCardOrder = orderOf(this.cards.charAt(i));
                int otherCardOrder = orderOf(o.getCards().charAt(i));
                int compareCard = Integer.compare(firstCardOrder, otherCardOrder);
                if (compareCard != 0) {
                    return compareCard;
                }
            }
            System.out.println("Both are equal");
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] -// %s //-> %d", cards, bestHandType.toString(), bid);
    }

    private int orderOf(char card) {
        return CARDS_ORDER.indexOf(card);
    }
}
