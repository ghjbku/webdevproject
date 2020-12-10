package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.TankDto;
import hu.unideb.webdev.exceptions.UnknownTankException;
import hu.unideb.webdev.model.KotlinTable;
import hu.unideb.webdev.service.KotlinTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TankController {
    private final KotlinTableService service;

    @GetMapping("/getAllTanks/")
    public Collection<TankDto> getTanks(@RequestParam(name = "type", defaultValue = "mediumTank/lightTank/heavyTank/SPG/AT-SPG", required = false) String type) throws UnknownTankException {
        if(type.equals("mediumTank/lightTank/heavyTank/SPG/AT-SPG")){
        return service.getAllTanks().stream().map(model->TankDto.builder()
                .id(model.getId())
                .name(model.getName())
                .type(model.getType())
                .img_src(model.getImg_src())
                .tier(model.getTier())
                .Country(model.getCountry())
                .Price(model.getPrice())
                .country_img(model.getCountry_img())
                .strength(model.getStrength())
                .build()
        ).collect(Collectors.toList());}
        else return service.getAllTanksByType(type).stream().map(model->TankDto.builder()
                .id(model.getId())
                .name(model.getName())
                .type(model.getType())
                .img_src(model.getImg_src())
                .tier(model.getTier())
                .Country(model.getCountry())
                .Price(model.getPrice())
                .country_img(model.getCountry_img())
                .strength(model.getStrength())
                .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/getTank/")
    public KotlinTable getTank(@RequestParam(name = "name", defaultValue = "T-34", required = true) String name, @RequestParam(name = "type", defaultValue = "mediumTank", required = true) String type, @RequestParam(name = "img_src", defaultValue = "string", required = false) String img_src) throws UnknownTankException {
        try {
            return service.getTankByNameAndType(name, type);
        }catch (UnknownTankException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping("/addTank")
    public void addTank(@RequestBody TankDto requestDto){
        try {
            service.createTank(new KotlinTable(
                    requestDto.getId(),
                    requestDto.getName(),
                    requestDto.getImg_src(),
                     requestDto.getType(),
                    requestDto.getTier(),
                    requestDto.getCountry(),
                    requestDto.getPrice(),
                    requestDto.getCountry_img(),
                    requestDto.getStrength()
            ));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/removeTank")
    public void removeTank(@RequestBody TankDto requestDto){
        try {
            service.deleteTank(new KotlinTable(
                    requestDto.getId(),
                    requestDto.getName(),
                    requestDto.getImg_src(),
                    requestDto.getType(),
                    requestDto.getTier(),
                    requestDto.getCountry(),
                    requestDto.getPrice(),
                    requestDto.getCountry_img(),
                    requestDto.getStrength()
            ));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
