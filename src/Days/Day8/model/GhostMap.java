package Days.Day8.model;

import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public class GhostMap {
    private final Map<String, Node> nodes;
    private final String rules;
    private String currentNode;
    private Integer currentIndex;
    private boolean targetFounded;
    private Integer cycleSize;

    public GhostMap(Map<String, Node> nodes, String rules, String initialNode) {
        this.nodes = nodes;
        this.rules = rules;
        this.currentNode = initialNode;
        this.currentIndex = 0;
        this.targetFounded = false;
        this.cycleSize = 0;
    }

    public boolean nextStep() {
        if (!this.targetFounded) {
            currentNode = this.rules.charAt(this.currentIndex) == 'R' ?
                    nodes.get(currentNode).getRight() :
                    nodes.get(currentNode).getLeft();
            this.targetFounded = currentNode.charAt(2) == 'Z';
            this.currentIndex = (this.currentIndex + 1) % rules.length();
            this.cycleSize++;
        }
        return this.targetFounded;
    }
}
