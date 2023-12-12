package Days.Day8.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {
    private String name;
    private String right;
    private String left;

}
