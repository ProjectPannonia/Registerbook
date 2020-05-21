package com.registerbook.registerbook.model.entities;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "error.name.empty")
    @Length(max = 50, message = "error.name.length")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "error.band.empty")
    @Length(max = 50,message = "error.band.length")
    @Column(name = "BAND")
    private String band;

    @NotEmpty(message = "error.instrument.empty")
    @Column(name = "INSTRUMENT")
    private String instrument;

    @NotEmpty(message = "error.country.empty")
    @Column(name = "COUNTRY")
    private String country;

    @NotEmpty(message = "error.address.empty")
    @Length(max = 150, message = "error.address.length")
    @Column(name = "ADDRESS")
    private String address;

    @Email(message = "error.email.email")
    @NotEmpty(message = "error.email.empty")
    @Length(max = 80, message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "YEAROFBIRTH")
    private int yearOfBirth;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", band='" + band + '\'' +
                ", instrument='" + instrument + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
