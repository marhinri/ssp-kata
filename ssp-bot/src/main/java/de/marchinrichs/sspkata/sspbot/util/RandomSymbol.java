package de.marchinrichs.sspkata.sspbot.util;

import java.util.Random;

public enum RandomSymbol {
    scissor,
    paper,
    stone;

    private static final Random PRNG = new Random();

    public static RandomSymbol randomSymbol()  {
        RandomSymbol[] symbols = values();
        return symbols[PRNG.nextInt(symbols.length)];
    }
}
