package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
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
    private CustomerDaoImp dao;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressRepository addressRepository;



    @Test
    void testCreateCustomer() throws UnknownCustomerException {

//        when(dao.queryCity(any(), any())).thenReturn(CityEntity.builder().build());
        doReturn(CustomerEntity.builder().email("Debrecen").last_name("fname").first_name("fname").build())
                .when(dao).queryCustomer(any(),any(),any());

        dao.createCustomer(getCustomer());

        verify(customerRepository,times(1)).save(any());
    }

    private Customer getCustomer() {
        return new Customer(
                0,
                "firstname",
                "lastname",
                "email",
                "string",
                0,
                new Timestamp((new Date()).getTime()).toString()
        );
    }

    @Test
    void testDeleteCustomer() throws UnknownCustomerException{
        Customer customer = getCustomer();

        //check if it throws an exception
        Throwable thrown = assertThrows(UnknownCustomerException.class, () ->dao.deleteCustomer(customer));

        assertThat(thrown.getMessage(), is(String.format("customer Not Found %s",customer)));
        assertThat(thrown.getCause(), instanceOf(UnknownCustomerException.class));

        dao.createCustomer(customer);
        dao.deleteCustomer(customer);
    }
}