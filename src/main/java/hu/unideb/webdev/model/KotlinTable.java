package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class KotlinTable {
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
