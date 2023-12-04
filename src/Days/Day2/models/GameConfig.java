package Days.Day2.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Map;

@Getter
@Builder
public class GameConfig {
    private Map<Color, Integer> colours;

}
