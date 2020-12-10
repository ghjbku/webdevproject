package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerDaoImp implements CustomerDao {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;
    private final AddressRepository addressRepository;

    private StoreEntity queryStore(int store) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findById(store);
        System.out.println(storeEntity);
        if (storeEntity.isPresent()) {
            log.trace("store Entity: {}", storeEntity);
            return storeEntity.get();

        }else throw new UnknownStoreException(String.format("Store with this ID does not Exist: %d", store));

    }

    private AddressEntity queryAddress(String address) {
        Optional<AddressEntity> addressEntity = StreamSupport.stream(addressRepository.findAll().spliterator(), false).filter(
                entity -> address.equals(entity.getAddress())
        ).findAny();
        if (addressEntity.isPresent()) {
            return addressEntity.get();
        } else {
            addressEntity = addressRepository.findById(1);
            addressRepository.save(addressEntity.get());
            return addressEntity.get();
        }
    }

    @Override
    public void createCustomer(Customer customer) throws UnknownStoreException {
        CustomerEntity customerEntity;
        System.out.println(customer.getStore());
        customerEntity = CustomerEntity.builder()
                .first_name(customer.getFirst_name())
                .last_name(customer.getLast_name())
                .email(customer.getEmail())
                .store(queryStore(customer.getStore()))
                .address(queryAddress(customer.getAddress()))
                .createDate(new java.sql.Date(new Date().getDate()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("CustomerEntity: {}", customerEntity);
        try {
            customerRepository.save(customerEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(entity -> new Customer(
                        entity.getStore().getId(),
                        entity.getFirst_name(),
                        entity.getLast_name(),
                        entity.getEmail(),
                        entity.getAddress().getAddress(),
                        entity.getActive(),
                        entity.getCreateDate().toString()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerEntity queryCustomer(String email, String Fname, String Lname) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return Fname.equals(entity.getFirst_name()) &&
                            Lname.equals(entity.getLast_name()) &&
                            email.equals(entity.getEmail());
                }
        ).findAny();
        if (customerEntity.isPresent()) {
            return customerEntity.get();
        } else throw new UnknownCustomerException(String.format("customer Not Found: %s", Fname));
    }

    @Override
    public void updateCustomer(int customerid,Customer newcustomer) throws UnknownCustomerException, UnknownStoreException {
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return customerid==entity.getId();
                }
        ).findAny();
        if (customerEntity.isPresent()) {
            customerEntity = Optional.ofNullable(CustomerEntity.builder()
                    .first_name(newcustomer.getFirst_name())
                    .last_name(newcustomer.getLast_name())
                    .email(newcustomer.getEmail())
                    .store(queryStore(newcustomer.getStore()))
                    .address(queryAddress(newcustomer.getAddress()))
                    .createDate(new java.sql.Date(new Date().getDate()))
                    .lastUpdate(new Timestamp((new Date()).getTime()))
                    .build());
            customerRepository.save(customerEntity.get());
        }else throw new UnknownCustomerException(String.format("customer Not Found: %d", customerid));
    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return customer.getFirst_name().equals(entity.getFirst_name()) &&
                            customer.getLast_name().equals(entity.getLast_name()) &&
                            customer.getEmail().equals(entity.getEmail());
                }
        ).findAny();

        if (customerEntity.isPresent()) {
            customerRepository.delete(customerEntity.get());
        } else throw new UnknownCustomerException(String.format("customer Not Found: %s", customer), customer);

    }


}
