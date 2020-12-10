package hu.unideb.webdev.exceptions;

import lombok.Data;

@Data
public class UnknownStoreException extends Exception {

    public UnknownStoreException(String message) {
        super(message);
    }
}
