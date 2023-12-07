package Days.Day3.models;

import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Builder
@Getter
public class PartNumber {
    private static String NOT_SYMBOLS = "0123456789.";
    private Set<Coordinate> coordinates;
    private int number;
    public boolean isAdyacentToSymbol(List<String> schematic){
        for(Coordinate coor : this.coordinates) {
            for (int y = max(coor.getY() - 1, 0); y <= min(coor.getY() + 1, schematic.size() - 1); y++) {
                for (int x = max(coor.getX() - 1, 0); x <= min(coor.getX() + 1, schematic.size() - 1); x++) {
                    char currentChar = schematic.get(y).charAt(x);
                    if (NOT_SYMBOLS.indexOf(currentChar) == -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Set<Coordinate> getAdyacentAsterisks(List<String> schematic){
        Set<Coordinate> symbols = new HashSet<>();
        for(Coordinate coor : this.coordinates) {
            for (int y = max(coor.getY() - 1, 0); y <= min(coor.getY() + 1, schematic.size() - 1); y++) {
                for (int x = max(coor.getX() - 1, 0); x <= min(coor.getX() + 1, schematic.size() - 1); x++) {
                    char currentChar = schematic.get(y).charAt(x);
                    if ("*".indexOf(currentChar)!=-1) {
                        symbols.add(new Coordinate(x, y));
                    }
                }
            }
        }
        return symbols;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
