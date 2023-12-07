package Days.Day2;

import Days.Day;
import Days.Day2.models.Color;
import Days.Day2.models.Game;
import Days.Day2.models.GameConfig;
import Days.Util;

import java.util.*;

public class Day2 extends Day {

    public Day2() {
        super(2);
    }

    @Override
    public void exec() {
        List<String> lines = Util.readFile(2);

        List<Game> games = lines.stream()
                .map(this::parseGame)
                .toList();

        part1(games, GameConfig.builder()
                .colours(Map.of(Color.RED, 12, Color.GREEN, 13, Color.BLUE, 14))
                .build());
        part2(games);
    }

    private void part1(List<Game> games, GameConfig configuration) {
        printStatementPart1();

        List<Integer> notPossibleIds = new ArrayList<>();
        for(Game game : games) {
            if(game.isPossible(configuration)){
                notPossibleIds.add(game.getId());
            }
        }
        int idsSum = notPossibleIds.stream().mapToInt(Integer::intValue).sum();
        System.out.println(notPossibleIds);
        System.out.println("Sum of ids is: " + idsSum);
    }

    private void part2(List<Game> games) {
        printStatementPart2();

        Integer power = games.stream()
                .map(Game::getPower)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(power);
    }

    private Game parseGame(String line) {
        int gameId = Integer.parseInt(line.split(":")[0].replace("Game ", ""));
        String coloursConfig = line.split(": ")[1];
        List<GameConfig> gameConfigs = new ArrayList<>();

        for(String set : coloursConfig.split("; ")){
            Map<Color, Integer> colours = new HashMap<>();
            for (String colourConfig : set.split(", ")) {
                String[] setTokens = colourConfig.split(" ");
                int number = Integer.parseInt(setTokens[0]);
                Color color = Color.valueOf(setTokens[1].toUpperCase());
                colours.put(color, number);
            }
            gameConfigs.add(GameConfig.builder()
                    .colours(colours)
                    .build());
        }

        return Game.builder()
                .id(gameId)
                .gameConfigs(gameConfigs)
                .build();
    }
}
