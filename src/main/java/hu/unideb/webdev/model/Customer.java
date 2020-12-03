package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Customer {
    private int store;
    private String First_name;
    private String Last_name;
    private String email;
    private String address;
    private int active;
    private String create_date;
}
