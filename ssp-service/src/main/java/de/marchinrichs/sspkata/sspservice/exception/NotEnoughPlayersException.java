package de.marchinrichs.sspkata.sspservice.exception;

public class NotEnoughPlayersException extends Throwable {
    private final String msg;

    public NotEnoughPlayersException(String s) {
        msg = s;
    }
}
