package Days.Day5.model;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public class Range {
    private BigInteger startDestination;
    private BigInteger startSource;
    private BigInteger length;

    public boolean isInRange(BigInteger source) {
        return startSource.compareTo(source)<=0 && source.compareTo(startSource.add(length)) <= 0;
    }

    public BigInteger getConversion(BigInteger source){
        if(isInRange(source)){
            BigInteger diff = source.subtract(this.startSource);
            return this.startDestination.add(diff);
        }else{
            return source;
        }
    }

    @Override
    public String toString() {
        return "[" + startDestination + ", " + startSource + ", " + length + "]";
    }
}
