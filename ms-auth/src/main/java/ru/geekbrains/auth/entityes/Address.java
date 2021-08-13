package ru.geekbrains.auth.entityes;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    public Address(){
        this.userDetails = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "country")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private int houseNumber;
    @Column(name = "apartment_number")
    private int apartmentNumber;
    @Column(name = "postcode")
    private long postcode;

    @ManyToMany(mappedBy = "addresses",fetch = FetchType.LAZY)
    private Collection<UserDetail> userDetails;
}
