package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TankDto {
    private int id;
    private String name;
    private String img_src;
    private String type;
    private int tier;
    private String Country;
    private int Price;
    private String country_img;
    private String strength;
}