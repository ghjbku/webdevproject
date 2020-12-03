package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.CustomerDto;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/getAllCustomer/")
    public Collection<CustomerDto> listCustomer(){
        return service.getAllCustomer().stream().map(model->CustomerDto.builder()
                .First_name(model.getFirst_name())
                .Last_name(model.getLast_name())
                .email(model.getEmail())
                .store(model.getStore())
                .active(model.getActive())
                .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/getCustomer/")
    public Customer queryCustomer(@RequestParam(name = "fname", defaultValue = "string", required = false) String fname, @RequestParam(name = "lname", defaultValue = "string", required = false) String lname, @RequestParam(name = "email", defaultValue = "string", required = false) String email){
        return service.getCustomerByFirstNameEmailAndLastName(fname, lname, email);
    }

    @PostMapping("/addCustomer")
    public void recordCustomer(@RequestBody CustomerDto requestDto){
        try {
            service.recordCustomer(new Customer(
                    requestDto.getStore(),
                    requestDto.getFirst_name(),
                    requestDto.getLast_name(),
                    requestDto.getEmail(),
                    requestDto.getAddress(),
                    requestDto.getActive(),
                    new Timestamp((new Date()).getTime()).toString()
            ));
        } catch (UnknownCustomerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/removeCustomer")
    public void removeCustomer(@RequestBody CustomerDto requestDto){
        try {
            service.deleteCustomer(new Customer(
                    requestDto.getStore(),
                    requestDto.getFirst_name(),
                    requestDto.getLast_name(),
                    requestDto.getEmail(),
                    requestDto.getAddress(),
                    requestDto.getActive(),
                    new Timestamp((new Date()).getTime()).toString()
            ));
        } catch (UnknownCustomerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
