package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.model.Customer;

import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 */
public interface CustomerDao {

    void createCustomer(Customer customer) throws UnknownCustomerException;
    CustomerEntity queryCustomer(String email, String Fname, String Lname) throws UnknownCustomerException;
    Collection<Customer> readAll();

    void deleteCustomer(Customer customer) throws  UnknownCustomerException;
}
