package Days.Day7.model;

import lombok.Getter;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class JokerHand extends Hand {
    private static String CARDS_ORDER = "J23456789TQKA";

    public JokerHand(BigInteger bid, String cards) {
        super(bid, cards);
    }

    public HandType calculateBestHandType() {
        if (!this.getCards().contains("J")) {
            return getHandType(this.getCards());
        } else if ("JJJJJ".equals(this.getCards())) {
            return HandType.FIVE_OF_A_KIND;
        } else {
            Set<String> handsWithoutJoker = getVariationHandsWithoutJoker(Set.of(this.getCards()));

            Set<HandType> types = new HashSet<>();
            for (String hand : handsWithoutJoker) {
                HandType type = getHandType(hand);
                if (HandType.FIVE_OF_A_KIND.equals(type)) {
                    return HandType.FIVE_OF_A_KIND;
                }
                types.add(type);
            }
            List<HandType> handTypes = new ArrayList<>(types);
            handTypes.sort((o1, o2) -> Integer.compare(o2.order, o1.order));
            return handTypes.get(0);
        }
    }

    public Set<String> getVariationHandsWithoutJoker(Set<String> handsWithJoker) {
        Set<String> handsWithoutJoker = new HashSet<>();

        for (String hand : handsWithJoker) {
            if (!hand.contains("J")) {
                handsWithoutJoker.add(hand);
            } else {
                Set<String> otherCharacters = this.getCards().chars()
                        .mapToObj(c -> (char) c)
                        .filter(c -> c != 'J')
                        .map(String::valueOf)
                        .collect(Collectors.toSet());
                Set<String> variations = new HashSet<>();
                for (String character : otherCharacters) {
                    variations.add(hand.replace("J", character));
                }
                handsWithoutJoker.addAll(getVariationHandsWithoutJoker(variations));
            }
        }
        return handsWithoutJoker;
    }

    private HandType getHandType(String cards) {
        Map<Character, Long> ocurrences = cards.chars()
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
    String getCardsOrder() {
        return CARDS_ORDER;
    }

}
