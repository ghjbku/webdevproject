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
@Table(name = "customer", schema = "sakila")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="store_id")
    private StoreEntity store;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name="address_id")
    private AddressEntity address;

    @Column
    private int active;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="last_update")
    private Timestamp lastUpdate;

}
