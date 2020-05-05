package com.registerbook.registerbook.service.statistics;

public class CountryAndNumber {

    private String country;
    private int numberFromThisCountry;

    public CountryAndNumber(String country, int numberFromThisCountry) {
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
