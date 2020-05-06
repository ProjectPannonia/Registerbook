package com.registerbook.registerbook.service.statistics;

public class MembersPerCountry {
    String country;
    int number;

    public MembersPerCountry(String country, int number) {
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
