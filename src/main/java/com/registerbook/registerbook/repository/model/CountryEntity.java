package com.registerbook.registerbook.repository.model;


import javax.persistence.*;

@Entity
@Table(name = "COUNTRY")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COUNTRYNAME")
    private String countryName;

    public CountryEntity(){

    }
    public CountryEntity(String countryName) {
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
