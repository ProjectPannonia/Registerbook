package com.registerbook.registerbook.service.statistics;

public class StatisticData {
    //$scope.countries = ['Germany','Sweden','Hungary','UK','USA','Norway','Finnland','Canada'];
    private int registeredMembers;
    private int numberOfBands;
    private int canadianBands;
    private int finnBands;
    private int germanBands;
    private int hungarianBands;
    private int norwayBands;
    private int swedenBands;
    private int ukBands;
    private int usaBands;

    public StatisticData(int registeredMembers, int numberOfBands, int canadianBands, int finnBands, int germanBands, int hungarianBands, int norwayBands, int swedenBands, int ukBands, int usaBands) {
        this.registeredMembers = registeredMembers;
        this.numberOfBands = numberOfBands;
        this.canadianBands = canadianBands;
        this.finnBands = finnBands;
        this.germanBands = germanBands;
        this.hungarianBands = hungarianBands;
        this.norwayBands = norwayBands;
        this.swedenBands = swedenBands;
        this.ukBands = ukBands;
        this.usaBands = usaBands;
    }
}
