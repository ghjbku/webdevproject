package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.AddressDto;
import hu.unideb.webdev.controller.dto.AddressRecordRequestDto;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.service.AddressService;
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
public class AddressController {
    private final AddressService service;

    @GetMapping("/address/")
    public Collection<AddressDto> listaddress(){
        return service.getAllAddress().stream().map(model->AddressDto.builder()
                .address(model.getAddress())
                .address2(model.getAddress2())
                .district(model.getDistrict())
                .city(model.getCity())
                .country(model.getCountry())
                .build()
        ).collect(Collectors.toList());
    }
    @PostMapping("/add")
    public void recordAddress(@RequestBody AddressRecordRequestDto requestDto){
        try {
            service.recordAddress(new Address(
                    requestDto.getAddress(),
                    requestDto.getAddress2(),
                    requestDto.getDistrict(),
                    requestDto.getCity(),
                    requestDto.getCountry(),
                    requestDto.getPostalCode(),
                    requestDto.getPhone()
            ));
        } catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
