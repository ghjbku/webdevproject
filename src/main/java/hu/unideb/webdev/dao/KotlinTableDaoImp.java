package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.AddressEntity;
import hu.unideb.webdev.dao.entity.CityEntity;
import hu.unideb.webdev.dao.entity.CountryEntity;
import hu.unideb.webdev.dao.entity.KotlinTableEntity;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.KotlinTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
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
public class KotlinTableDaoImp implements KotlinTableDao{

    private final KotlinTableRepository tankrepository;


/*   @Override
    public void createAddress(Address address) throws UnknownCountryException {
        AddressEntity addressEntity;
        GeometryFactory geometryFactory = new GeometryFactory();

        addressEntity = AddressEntity.builder()
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .postalCode(address.getPostalCode())
                .phone(address.getPhone())
                .location(geometryFactory.createPoint(new Coordinate()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .city(queryCity(address.getCity(), address.getCountry()))
                .build();
        log.info("AddressEntity: {}", addressEntity);
        try {
            addressRepository.save(addressEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }*/

    @Override
    public void createTank(KotlinTable kotlinTable) {

    }

    @Override
    public KotlinTableEntity queryTank(String name, String type) {
        return null;
    }

    @Override
    public Collection<KotlinTable> readAll() {
        return StreamSupport.stream(tankrepository.findAll().spliterator(),false)
                .map(entity -> new KotlinTable(
                        entity.getId(),
                        entity.getName(),
                        entity.getImg_src(),
                        entity.getType(),
                        entity.getTier(),
                        entity.getCountry(),
                        entity.getPrice(),
                        entity.getCountry_img(),
                        entity.getStrength()

                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTank(KotlinTable kotlinTable) {
        Optional<KotlinTableEntity> tankEntity = StreamSupport.stream(tankrepository.findAll().spliterator(),false).filter(
                entity ->{
                    return kotlinTable.getName().equals(entity.getName())  &&
                            kotlinTable.getType().equals(entity.getType());
                }
        ).findAny();
        tankrepository.delete(tankEntity.get());
    }

}
