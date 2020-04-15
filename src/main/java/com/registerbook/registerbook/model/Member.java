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
    @NotEmpty
    @Length(max = 50)
    @Column(name = "name")
    private String name;
    @NotEmpty
    @Length(max = 50)
    @Column(name = "BAND")
    private String band;
    @NotEmpty
    @Length(max = 150)
    @Column(name = "ADDRESS")
    private String address;
    @Email(message = "error.email.email")
    @NotEmpty(message = "error.mail.empty")
    @Length(max = 80, message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;
    @NotEmpty
    @Length(max = 50)
    @Column(name = "FAVOURITEANIMAL")
    private String favouriteAnimal;
    @NotEmpty
    @Length(max = 50)
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
