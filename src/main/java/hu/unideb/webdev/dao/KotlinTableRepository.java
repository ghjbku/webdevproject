package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.dao.entity.KotlinTableEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KotlinTableRepository extends CrudRepository<KotlinTableEntity, Integer> {

    @Override
    Optional<KotlinTableEntity> findById(Integer integer);

}
