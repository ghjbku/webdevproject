package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.exceptions.UnknownTankException;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.model.KotlinTable;

import java.util.Collection;

public interface KotlinTableService {

    Collection<KotlinTable> getAllTanks();
    Collection<KotlinTable> getAllTanksByType(String type) throws UnknownTankException;

    KotlinTable getTankByNameAndType(String name, String type) throws UnknownTankException;

    void createTank(KotlinTable kotlinTable);

    void deleteTank(KotlinTable kotlinTable);

}
