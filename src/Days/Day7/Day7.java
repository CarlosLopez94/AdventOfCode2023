package Days.Day7;

import Days.Day;
import Days.Day7.model.JokerHand;
import Days.Day7.model.NormalHand;
import Days.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day7 extends Day {

    public Day7() {
        super(7);
    }

    @Override
    public void exec() {
        List<String> input = Util.readFile(7);

        part1(input);
        part2(input);
    }

    private void part1(List<String> input) {
        printStatementPart1();

        // parse
        List<NormalHand> hands = new ArrayList<>();
        for (String line : input) {
            String[] tokens = line.split(" ");
            String cards = tokens[0];
            BigInteger bid = new BigInteger(tokens[1]);
            hands.add(new NormalHand(bid, cards));
        }
        Collections.sort(hands);
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= hands.size(); i++) {
            sum = sum.add(BigInteger.valueOf(i).multiply(hands.get(i - 1).getBid()));
        }
        System.out.println("Total winnings is: " + sum);
    }

    private void part2(List<String> input) {
        printStatementPart2();

        // parse
        List<JokerHand> hands = new ArrayList<>();
        for (String line : input) {
            String[] tokens = line.split(" ");
            String cards = tokens[0];
            BigInteger bid = new BigInteger(tokens[1]);
            hands.add(new JokerHand(bid, cards));
        }

        Collections.sort(hands);

        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= hands.size(); i++) {
            sum = sum.add(BigInteger.valueOf(i).multiply(hands.get(i - 1).getBid()));
        }

        System.out.println("Total winnings is: " + sum);
    }
}
