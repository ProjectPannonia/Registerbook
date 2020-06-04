package com.registerbook.registerbook.service.register.statistics;

import com.registerbook.registerbook.service.register.statistics.UpdatedStatistic.CountryAndQuantity;

import java.util.List;

public class StatisticData {

    private int registeredMembers;
    private int numberOfBands;
    private List<CountryAndQuantity> membersPerCountry;

    public StatisticData(int registeredMembers, int numberOfBands, List<CountryAndQuantity> membersPerCountry) {
        this.registeredMembers = registeredMembers;
        this.numberOfBands = numberOfBands;
        this.membersPerCountry = membersPerCountry;
    }

    public int getRegisteredMembers() {
        return registeredMembers;
    }

    public void setRegisteredMembers(int registeredMembers) {
        this.registeredMembers = registeredMembers;
    }

    public int getNumberOfBands() {
        return numberOfBands;
    }

    public void setNumberOfBands(int numberOfBands) {
        this.numberOfBands = numberOfBands;
    }

    public List<CountryAndQuantity> getMembersPerCountry() {
        return membersPerCountry;
    }

    public void setMembersPerCountry(List<CountryAndQuantity> membersPerCountry) {
        this.membersPerCountry = membersPerCountry;
    }
}
