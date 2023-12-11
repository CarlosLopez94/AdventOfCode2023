package Days.Day5;

import Days.Day;
import Days.Day5.model.Almanac;
import Days.Day5.model.Convertions;
import Days.Day5.model.Range;
import Days.Util;

import java.math.BigInteger;
import java.time.Instant;
import java.util.*;

public class Day5 extends Day {

    public Day5() {
        super(5);
    }

    @Override
    public void exec() {
        List<String> input = Util.readFile(5);

        List<BigInteger> seeds = Arrays.stream(input.get(0).replaceAll("seeds: ", "").split(" "))
                .map(BigInteger::new)
                .toList();
        Map<Convertions, Almanac> almanacs = parseToAlmanacs(input);

        part1(seeds, almanacs);
        part2(seeds, almanacs);
    }

    private void part1(List<BigInteger> seeds, Map<Convertions, Almanac> almanacs) {
        printStatementPart1();

        Map<BigInteger, BigInteger> locations = new HashMap<>();
        for (BigInteger seed : seeds) {
            BigInteger soil = almanacs.get(Convertions.SEED_TO_SOIL).getConversion(seed);
            BigInteger fertilizer = almanacs.get(Convertions.SOIL_TO_FERTILIZER).getConversion(soil);
            BigInteger water = almanacs.get(Convertions.FERTILIZER_TO_WATER).getConversion(fertilizer);
            BigInteger light = almanacs.get(Convertions.WATER_TO_LIGHT).getConversion(water);
            BigInteger temperature = almanacs.get(Convertions.LIGHT_TO_TEMPERATURE).getConversion(light);
            BigInteger humidity = almanacs.get(Convertions.TEMPERATURE_TO_HUMIDITY).getConversion(temperature);
            BigInteger location = almanacs.get(Convertions.HUMIDITY_TO_LOCATION).getConversion(humidity);

            locations.put(seed, location);
        }

        System.out.println(locations);
        BigInteger lowestLocation = locations.values().stream().min(BigInteger::compareTo).get();
        System.out.println("The lowest location is: " + lowestLocation);
    }

    private void part2(List<BigInteger> seeds, Map<Convertions, Almanac> almanacs) {
        printStatementPart2();

        // brute force, 
        Set<BigInteger> alreadyCalculated = new HashSet<>();
        BigInteger lowestLocation = null;
        for (int i = 0; i < seeds.size(); i += 2) {
            BigInteger seed = seeds.get(i);
            BigInteger lengthRange = seeds.get(i + 1);
            for(BigInteger offset = BigInteger.ZERO; offset.compareTo(lengthRange)<0; offset = offset.add(BigInteger.ONE)){
                BigInteger newSeed = seed.add(offset);
                if(!alreadyCalculated.contains(newSeed)) {
                    BigInteger soil = almanacs.get(Convertions.SEED_TO_SOIL).getConversion(newSeed);
                    BigInteger fertilizer = almanacs.get(Convertions.SOIL_TO_FERTILIZER).getConversion(soil);
                    BigInteger water = almanacs.get(Convertions.FERTILIZER_TO_WATER).getConversion(fertilizer);
                    BigInteger light = almanacs.get(Convertions.WATER_TO_LIGHT).getConversion(water);
                    BigInteger temperature = almanacs.get(Convertions.LIGHT_TO_TEMPERATURE).getConversion(light);
                    BigInteger humidity = almanacs.get(Convertions.TEMPERATURE_TO_HUMIDITY).getConversion(temperature);
                    BigInteger location = almanacs.get(Convertions.HUMIDITY_TO_LOCATION).getConversion(humidity);

                    alreadyCalculated.add(newSeed);
                    if (lowestLocation == null) {
                        lowestLocation = location;
                    } else {
                        lowestLocation = lowestLocation.min(location);
                    }
                }
            }
            System.out.println("next! current lowest --> " + lowestLocation);
        }

        System.out.println("The lowest location is: " + lowestLocation);
    }

    private static Map<Convertions, Almanac> parseToAlmanacs(List<String> input) {
        Map<Convertions, Almanac> almanacs = new HashMap<>();
        Almanac almanac = null;
        for (int i = 2; i < input.size(); i++) {
            String line = input.get(i);
            if (line.contains("map:")) {
                if (almanac != null) {
                    almanacs.put(Convertions.valueOf(almanac.getName().replaceAll("-", "_")
                            .toUpperCase()), almanac);
                }
                almanac = new Almanac(line.replaceAll(" map:", ""));
            } else if (!line.isEmpty() && !line.contains("seeds: ")) {
                String[] tokens = line.split(" ");
                Objects.requireNonNull(almanac).addRange(Range.builder()
                        .startDestination(new BigInteger(tokens[0]))
                        .startSource(new BigInteger(tokens[1]))
                        .length(new BigInteger(tokens[2]))
                        .build());
            }
        }
        almanacs.put(Convertions.valueOf(almanac.getName().replaceAll("-", "_")
                .toUpperCase()), almanac);

        return almanacs;
    }

}
