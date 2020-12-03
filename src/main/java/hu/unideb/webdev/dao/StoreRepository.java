package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {

    @Override
    Optional<StoreEntity> findById(Integer integer);
}
