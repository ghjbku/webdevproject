package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.model.Customer;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomer();

    Customer getCustomerByFirstNameEmailAndLastName(String first_name,String LastName, String email);

    void recordCustomer(Customer customer) throws UnknownCustomerException;

    void deleteCustomer(Customer customer) throws UnknownCustomerException;

}
