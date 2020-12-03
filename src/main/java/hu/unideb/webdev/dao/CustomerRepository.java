package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CountryEntity;
import hu.unideb.webdev.dao.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    @Override
    Optional<CustomerEntity> findById(Integer integer);

}
