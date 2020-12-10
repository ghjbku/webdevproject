package hu.unideb.webdev.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "kotlintable", schema = "sakila")
public class KotlinTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "img_src")
    private String img_src;

    @Column(name = "type")
    private String type;

    @Column(name = "tier")
    private int tier;

    @Column(name = "Country")
    private String Country;

    @Column(name = "Price")
    private int Price;

    @Column(name = "country_img")
    private String country_img;

    @Column(name = "strength")
    private String strength;
}
