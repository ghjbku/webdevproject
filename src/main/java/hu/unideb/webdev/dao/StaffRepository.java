package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.StaffEntity;
import hu.unideb.webdev.dao.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {

    @Override
    Optional<StaffEntity> findById(Integer integer);
}
