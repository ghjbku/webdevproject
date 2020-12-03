package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.CustomerDao;
import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerDao customerDao;

    @Override
    public Collection<Customer> getAllCustomer() {
        return customerDao.readAll();
    }

    @Override
    public Customer getCustomerByFirstNameEmailAndLastName(String first_name,String LastName, String email) {
        Optional<Customer> customer =
        customerDao.readAll().stream()
                .filter(names -> first_name.equals(names.getFirst_name()))
                .filter(lname ->LastName.equals(lname.getLast_name()))
                .filter(mails->email.equals(mails.getEmail()))
                .findAny();
        return customer.get();
    }

    @Override
    public void recordCustomer(Customer customer) throws UnknownCustomerException {
            customerDao.createCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException {
        customerDao.deleteCustomer(customer);
    }
}