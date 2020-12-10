package hu.unideb.webdev.service;


import hu.unideb.webdev.dao.KotlinTableDao;
import hu.unideb.webdev.exceptions.UnknownTankException;
import hu.unideb.webdev.model.KotlinTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KotlinTableServiceImpl implements KotlinTableService{

    private final KotlinTableDao tankdao;

    @Override
    public Collection<KotlinTable> getAllTanks() {
        return tankdao.readAll();
    }

    @Override
    public Collection<KotlinTable> getAllTanksByType(String type) throws UnknownTankException {
        Collection<KotlinTable> tank =
                tankdao.readAll().stream()
                        .filter(types -> type.equals(types.getType())).collect(Collectors.toList());
        if (!tank.isEmpty()) {
            return tank;
        }else throw new UnknownTankException(String.format("tank Not Found: %s",type));
    }

    @Override
    public KotlinTable getTankByNameAndType(String name, String type) throws UnknownTankException {
        Optional<KotlinTable> tank =
                tankdao.readAll().stream()
                        .filter(names -> name.equals(names.getName()))
                        .filter(types -> type.equals(types.getType()))
                        .findAny();
        if (tank.isPresent()) {
            return tank.get();
        }else throw new UnknownTankException(String.format("tank Not Found: %s %s",name,type));
    }

    @Override
    public void createTank(KotlinTable kotlinTable) {
            tankdao.createTank(kotlinTable);
    }

    @Override
    public void deleteTank(KotlinTable kotlinTable) {tankdao.deleteTank(kotlinTable);
    }
}