package com.registerbook.registerbook.service.register.specialObjectsForStatistics;

public class StatisticData {

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

    public StatisticData(){

    }

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

    public int getCanadianBands() {
        return canadianBands;
    }

    public void setCanadianBands(int canadianBands) {
        this.canadianBands = canadianBands;
    }

    public int getFinnBands() {
        return finnBands;
    }

    public void setFinnBands(int finnBands) {
        this.finnBands = finnBands;
    }

    public int getGermanBands() {
        return germanBands;
    }

    public void setGermanBands(int germanBands) {
        this.germanBands = germanBands;
    }

    public int getHungarianBands() {
        return hungarianBands;
    }

    public void setHungarianBands(int hungarianBands) {
        this.hungarianBands = hungarianBands;
    }

    public int getNorwayBands() {
        return norwayBands;
    }

    public void setNorwayBands(int norwayBands) {
        this.norwayBands = norwayBands;
    }

    public int getSwedenBands() {
        return swedenBands;
    }

    public void setSwedenBands(int swedenBands) {
        this.swedenBands = swedenBands;
    }

    public int getUkBands() {
        return ukBands;
    }

    public void setUkBands(int ukBands) {
        this.ukBands = ukBands;
    }

    public int getUsaBands() {
        return usaBands;
    }

    public void setUsaBands(int usaBands) {
        this.usaBands = usaBands;
    }
}
