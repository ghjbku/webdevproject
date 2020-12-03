package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Customer;
import lombok.Data;

@Data
public class UnknownCustomerException extends Exception {

    private Customer customer;

    public UnknownCustomerException(Customer customer) {
        this.customer = customer;
    }

    public UnknownCustomerException(String message, Customer customer) {
        super(message);
        this.customer = customer;
    }

    public UnknownCustomerException(String message) {
        super(message);
    }
}
