package com.registerbook.registerbook.service.register.statistics;

public class MembersOfSpecifiedCountry {

    private String country;
    private int numberFromThisCountry;

    public MembersOfSpecifiedCountry(String country, int numberFromThisCountry) {
        this.country = country;
        this.numberFromThisCountry = numberFromThisCountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberFromThisCountry() {
        return numberFromThisCountry;
    }

    public void setNumberFromThisCountry(int numberFromThisCountry) {
        this.numberFromThisCountry = numberFromThisCountry;
    }
}