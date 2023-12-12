package Days.Day7.model;

import lombok.Getter;

import java.math.BigInteger;

@Getter
abstract class Hand implements Comparable<Hand> {
    private BigInteger bid;
    private String cards;
    private final HandType bestHandType;

    public Hand(BigInteger bid, String cards) {
        this.bid = bid;
        this.cards = cards;
        this.bestHandType = calculateBestHandType();
    }

    abstract HandType calculateBestHandType();

    abstract String getCardsOrder();

    private int orderOf(char card) {
        return getCardsOrder().indexOf(card);
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

}
