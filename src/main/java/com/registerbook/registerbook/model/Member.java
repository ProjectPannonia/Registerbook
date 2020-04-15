package com.registerbook.registerbook.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "error.address.empty")
    @Length(max = 150, message = "error.address.length")
    @Column(name = "ADDRESS")
    private String address;
    @Email(message = "error.email.email")
    @NotEmpty(message = "error.email.empty")
    @Length(max = 80, message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;
    @NotEmpty(message = "error.favouriteA.empty")
    @Length(max = 50, message = "error.favouriteA.length")
    @Column(name = "FAVOURITEANIMAL")
    private String favouriteAnimal;
    @NotEmpty(message = "error.favouriteM.empty")
    @Length(max = 50, message = "error.favouriteM.length")
    @Column(name = "FAVOURITEMEAL")
    private String favouriteMeal;

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

    public String getFavouriteAnimal() {
        return favouriteAnimal;
    }

    public void setFavouriteAnimal(String favouriteAnimal) {
        this.favouriteAnimal = favouriteAnimal;
    }

    public String getFavouriteMeal() {
        return favouriteMeal;
    }

    public void setFavouriteMeal(String favouriteMeal) {
        this.favouriteMeal = favouriteMeal;
    }
}
