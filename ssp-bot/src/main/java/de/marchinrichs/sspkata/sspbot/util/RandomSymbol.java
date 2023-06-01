package de.marchinrichs.sspkata.sspbot.util;

import java.util.Random;

public enum RandomSymbol {
    ROCK("rock"),
    PAPER("paper"),
    SCISSORS("scissors");

    private String value;

    RandomSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Random PRNG = new Random();

    public static RandomSymbol randomSymbol()  {
        RandomSymbol[] symbols = values();
        return symbols[PRNG.nextInt(symbols.length)];
    }
}
