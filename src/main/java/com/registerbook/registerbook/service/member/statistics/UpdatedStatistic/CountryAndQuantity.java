package com.registerbook.registerbook.service.member.statistics.UpdatedStatistic;

public class CountryAndQuantity {
    String country;
    int number;

    public CountryAndQuantity(String country, int number) {
        this.country = country;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
