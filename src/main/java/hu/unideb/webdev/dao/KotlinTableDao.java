package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.dao.entity.KotlinTableEntity;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.model.KotlinTable;

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
public interface KotlinTableDao {

    void createTank(KotlinTable kotlinTable);
    KotlinTableEntity queryTank(String name, String type);
    Collection<KotlinTable> readAll();

    void deleteTank(KotlinTable kotlinTable);
}
