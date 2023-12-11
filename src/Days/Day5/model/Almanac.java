package Days.Day5.model;

import lombok.Getter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Almanac {
    private String name;
    private Set<Range> ranges;

    public Almanac(String name) {
        this.name = name;
        this.ranges = new HashSet<>();
    }

    public void addRange(Range range){
        this.ranges.add(range);
    }

    public BigInteger getConversion(BigInteger source){
        for(Range range : this.ranges){
            if(range.isInRange(source)){
                return range.getConversion(source);
            }
        }
        return source;
    }

    @Override
    public String toString() {
        return "Almanac{" +
                "id='" + name + '\'' +
                ", ranges=" + ranges +
                '}';
    }
}
