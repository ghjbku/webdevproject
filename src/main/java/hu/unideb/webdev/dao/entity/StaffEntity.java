package hu.unideb.webdev.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "staff", schema = "sakila")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="staff_id")
    private int id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @ManyToOne
    @JoinColumn(name="address_id")
    private AddressEntity address;

    @Column
    private String email;

    @Column
    private int active;

    @Column
    private String username;

    @Column
    private String password;

    @Column(name="last_update")
    private Timestamp lastUpdate;
}
