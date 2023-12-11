package Days.Day6.model;

import lombok.Builder;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Builder
public class Race {
    private static BigInteger VELOCITY = BigInteger.ONE;
    private BigInteger time;
    private BigInteger distance;

    public Set<BigInteger> getHoldTimesThatBeatsRecord() {
        Set<BigInteger> beatRecordsHoldTimes = new HashSet<>();
        BigInteger currentHoldTime = BigInteger.ONE;
        boolean finished = false;
        while (!finished && currentHoldTime.compareTo(time) <= 0) {
            BigInteger currentDistance = calculateDistanceWithHold(currentHoldTime);
            if (currentDistance.compareTo(distance) > 0) {
                beatRecordsHoldTimes.add(currentHoldTime);
            } else {
                finished = !beatRecordsHoldTimes.isEmpty();
            }
            currentHoldTime = currentHoldTime.add(BigInteger.ONE);
        }
        return beatRecordsHoldTimes;
    }

    private BigInteger calculateDistanceWithHold(BigInteger holdTime) {
        BigInteger vel = VELOCITY.multiply(holdTime);
        return this.time.subtract(holdTime).multiply(vel);
    }

}
