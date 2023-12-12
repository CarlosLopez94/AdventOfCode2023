package Days.Day8;

import Days.Day;
import Days.Day8.model.GhostMap;
import Days.Day8.model.Node;
import Days.Util;

import java.util.*;
import java.util.stream.Collectors;

import static Days.Util.lcm;

public class Day8 extends Day {

    public Day8() {
        super(8);
    }

    @Override
    public void exec() {
        List<String> input = Util.readFile(8);

        String rules = input.get(0);
        Map<String, Node> nodes = parseNodes(input);

        part1(rules, nodes);
        part2(rules, nodes);
    }

    private void part1(String rules, Map<String, Node> nodes) {
        printStatementPart1();

        GhostMap ghostMap = new GhostMap(nodes, rules, "AAA");

        int steps = 0;
        boolean foundedZ = false;
        while (!foundedZ) {
            foundedZ = ghostMap.nextStep();
            steps++;
        }

        System.out.println("Steps are: " + steps);
    }

    private void part2(String rules, Map<String, Node> nodes) {
        printStatementPart2();

        Set<GhostMap> ghostMaps = nodes.keySet().stream()
                .filter(nodeName -> nodeName.endsWith("A"))
                .map(initialNode -> new GhostMap(nodes, rules, initialNode))
                .collect(Collectors.toSet());

        boolean foundedZ = false;
        while (!foundedZ) {
            foundedZ = ghostMaps.stream().allMatch(GhostMap::nextStep);
        }
        List<Long> cycles = ghostMaps.stream()
                .map(GhostMap::getCycleSize)
                .map(Integer::longValue)
                .toList();
        long lowerCommonMultiple = lcm(cycles);
        System.out.println("Steps are: " + lowerCommonMultiple);
    }

    private Map<String, Node> parseNodes(List<String> input) {
        Map<String, Node> nodes = new HashMap<>();
        for (int i = 2; i < input.size(); i++) {
            String[] tokens = input.get(i).split(" = ");
            String directions = tokens[1].trim().replace("(", "").replace(",", "").replace(")", "");
            String name = tokens[0];
            String left = directions.split(" ")[0];
            String right = directions.split(" ")[1];
            nodes.put(name, Node.builder()
                    .name(name)
                    .left(left)
                    .right(right)
                    .build()
            );
        }
        return nodes;
    }

}