package com.registerbook.registerbook.model;

import javax.persistence.*;

@Entity
@Table(name = "RegisteredCountries")
public class RegisteredCountries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CountryName")
    private String countryName;

}
