package Days.Day3.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
