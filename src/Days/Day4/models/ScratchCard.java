package Days.Day4.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ScratchCard {
    private int id;
    private Set<Integer> winningNumber;
    private Set<Integer> numbers;

    public Set<Integer> getCardWinningNumbers(){
        return numbers.stream()
                .filter(n -> winningNumber.contains(n))
                .collect(Collectors.toSet());
    }

    public int getPoints(){
        return Math.max((int) Math.pow(2, getCardWinningNumbers().size() - 1), 0);
    }

}
