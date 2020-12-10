package hu.unideb.webdev.exceptions;

public class UnknownTankException extends Exception {

    public UnknownTankException() {
    }

    public UnknownTankException(String message) {
        super(message);
    }
}
