package Days.Day7.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigInteger;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class NormalHand extends Hand {
    private static String CARDS_ORDER = "23456789TJQKA";

    public NormalHand(BigInteger bid, String cards) {
        super(bid, cards);
    }

    @Override
    public HandType calculateBestHandType() {
        Map<Character, Long> ocurrences = this.getCards().chars()
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
    public String getCardsOrder() {
        return CARDS_ORDER;
    }
}
