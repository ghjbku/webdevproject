package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.sql.Timestamp;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class CustomerDaoImplTest {

    @Spy
    @InjectMocks
    private AddressDaoImpl addressDao;
    @InjectMocks
    private CustomerDaoImp dao;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private StaffRepository staffRepository;


    @Test
    void testCreateCustomer() throws UnknownStoreException, UnknownCountryException {
        AddressEntity address = addressRepository.save(AddressEntity.builder().city(CityEntity.builder().name("Debrecen").build()).build());
        System.out.println(AddressEntity.builder().city(CityEntity.builder().name("Debrecen").build()).build());
        StaffEntity asd = staffRepository.save(StaffEntity.builder().username("asd").password("asd").last_name("asd").first_name("asd").email("0")
                                                            .active(1).address(address).build());

        System.out.println(storeRepository.save(StoreEntity.builder().id(1).manager_staff_id(any()).address(AddressEntity.builder().build()).build()));

        dao.createCustomer(getCustomer());

        verify(customerRepository,times(1)).save(any());
    }

    private Customer getCustomer() {
        return new Customer(
                1,
                "f",
                "l",
                "em",
                "adr",
                0,
                new Timestamp((new Date()).getTime()).toString()
        );
    }

    @Test
    void testDeleteCustomerThrowsExcpetion() {
        Customer customer = getCustomer();

        //check if it throws an exception
        Throwable thrown = assertThrows(UnknownCustomerException.class, () ->dao.deleteCustomer(customer));

        assertThat(thrown.getMessage(), is(String.format("customer Not Found: %s",customer)));
    }
     @Test
     void testDeleteCustomerGoesThrough() throws UnknownStoreException, UnknownCustomerException {
         dao.createCustomer(getCustomer());
         dao.deleteCustomer(getCustomer());
     }

    private Address getAddress() {
        return new Address(
                "address1",
                "address2",
                "district",
                "UnknownCity",
                "Algeria_1234",
                "postalCode",
                "phone"
        );
    }


}