package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.AddressEntity;
import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.dao.entity.StoreEntity;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
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
public class CustomerDaoImp implements CustomerDao{

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private StoreEntity queryStore(int store,String address) {
        Optional<StoreEntity> storeEntity = storeRepository.findById(store).stream()
                .filter(entity -> (entity.getId() == store))
                .findFirst();
        if(!storeEntity.isPresent()){
            storeEntity = Optional.ofNullable(StoreEntity.builder()
                    .address(queryAddress(address))
                    .lastUpdate(new Timestamp((new Date()).getTime()))
                    .build());
            storeRepository.save(storeEntity.get());
            log.info("Recorded new store: {}", store);
        }
        log.trace("store Entity: {}", storeEntity);
        return storeEntity.get();
    }
    private AddressEntity queryAddress(String address) {
        Optional<AddressEntity> addressEntity = StreamSupport.stream(addressRepository.findAll().spliterator(),false).filter(
                entity -> address.equals(entity.getAddress())
        ).findAny();
        return addressEntity.get();
    }

    @Override
    public void createCustomer(Customer customer) {
        CustomerEntity customerEntity;

        customerEntity = CustomerEntity.builder()
                .first_name(customer.getFirst_name())
                .last_name(customer.getLast_name())
                .email(customer.getEmail())
                .store(queryStore(customer.getStore(),customer.getAddress()))
                .address(queryAddress(customer.getAddress()))
                .createDate(new java.sql.Date(new Date().getDate()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("CustomerEntity: {}", customerEntity);
        try {
            customerRepository.save(customerEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(),false)
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
    public CustomerEntity queryCustomer(String email,String Fname,String Lname) throws UnknownCustomerException{
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return Fname.equals(entity.getFirst_name()) &&
                            Lname.equals(entity.getLast_name()) &&
                            email.equals(entity.getEmail());
                }
        ).findAny();
        return customerEntity.get();
    }
    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return customer.getFirst_name().equals(entity.getFirst_name()) &&
                           customer.getLast_name().equals(entity.getLast_name()) &&
                           customer.getEmail().equals(entity.getEmail());
                }
        ).findAny();
        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException(String.format("customer Not Found %s",customer), customer);
        }
        customerRepository.delete(customerEntity.get());
    }


}
