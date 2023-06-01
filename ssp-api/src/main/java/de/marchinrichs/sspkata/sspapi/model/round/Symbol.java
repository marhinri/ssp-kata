package de.marchinrichs.sspkata.sspapi.model.round;

public enum Symbol {
    ROCK("rock"),
    PAPER("paper"),
    SCISSORS("scissors");
    private String value;

    Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
