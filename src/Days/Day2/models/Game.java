package Days.Day2.models;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class Game {
    private int id;
    private List<GameConfig> gameConfigs;

    public boolean isPossible(GameConfig initialConfig){
        for(GameConfig gameConfig : gameConfigs){
            for(Color color : gameConfig.getColours().keySet()){
                if(gameConfig.getColours().get(color) > initialConfig.getColours().getOrDefault(color, 0)){
                    return false;
                }
            }
        }
        return true;
    }

    public GameConfig getMinimumConfiguration(){
        Map<Color, Integer> minimumColours = new HashMap<>();
        for(GameConfig gameConfig : gameConfigs){
            for(Color color : gameConfig.getColours().keySet()){
               if(gameConfig.getColours().get(color) > minimumColours.getOrDefault(color, 0)){
                   minimumColours.put(color, gameConfig.getColours().get(color));
               }
            }
        }
        return GameConfig.builder()
                .colours(minimumColours)
                .build();
    }

    public Integer getPower(){
        GameConfig minimumConfig = getMinimumConfiguration();
        return minimumConfig.getColours().getOrDefault(Color.RED, 1) *
                minimumConfig.getColours().getOrDefault(Color.GREEN, 1) *
                minimumConfig.getColours().getOrDefault(Color.BLUE, 1);
    }

}
