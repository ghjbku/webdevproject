package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomer();

    Customer getCustomerByFirstNameEmailAndLastName(String first_name,String LastName, String email) throws UnknownCustomerException;

    void recordCustomer(Customer customer) throws UnknownCustomerException, UnknownStoreException;

    void deleteCustomer(Customer customer) throws UnknownCustomerException;

}
