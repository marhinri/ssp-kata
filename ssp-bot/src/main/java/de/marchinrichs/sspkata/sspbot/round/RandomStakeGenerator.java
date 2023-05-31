package de.marchinrichs.sspkata.sspbot.round;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class RandomStakeGenerator {
    private static final Random PRNG = new Random();

    public int randomStake(int maxValue)  {
        int[] numbers = IntStream.rangeClosed(0, maxValue).toArray();
        return numbers[PRNG.nextInt(numbers.length)];
    }
}
