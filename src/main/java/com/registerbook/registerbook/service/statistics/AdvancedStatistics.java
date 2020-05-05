package com.registerbook.registerbook.service.statistics;

import com.registerbook.registerbook.model.Member;

import java.util.ArrayList;
import java.util.List;

public class AdvancedStatistics {

    public StatisticData getStatistics(List<Member> allMembers){
        return creteStatistics(allMembers);
    }

    private StatisticData creteStatistics(List<Member> allMembers){
        StatisticData result = new StatisticData();
        List<MembersOfSpecifiedCountry> numberOfMembersPerCountry = countMembersPerCountry(allMembers);
        //"Canada","Finnland","Germany","Hungary","Norway","Sweden","UK","USA"};
        result.setRegisteredMembers(allMembers.size());
        result.setNumberOfBands(countRegisteredBands(allMembers)); // implement
        result.setCanadianBands(numberOfMembersPerCountry.get(0).getNumberFromThisCountry());
        result.setFinnBands(numberOfMembersPerCountry.get(1).getNumberFromThisCountry());
        result.setGermanBands(numberOfMembersPerCountry.get(2).getNumberFromThisCountry());
        result.setHungarianBands(numberOfMembersPerCountry.get(3).getNumberFromThisCountry());
        result.setNorwayBands(numberOfMembersPerCountry.get(4).getNumberFromThisCountry());
        result.setSwedenBands(numberOfMembersPerCountry.get(5).getNumberFromThisCountry());
        result.setUkBands(numberOfMembersPerCountry.get(6).getNumberFromThisCountry());
        result.setUsaBands(numberOfMembersPerCountry.get(7).getNumberFromThisCountry());
        return result;
    }
    private int countRegisteredBands(List<Member> allMembers){
        ArrayList<String> bands = new ArrayList<>();
        for(Member m : allMembers){
            String actual = m.getBand();
            if(bands.contains(actual)){
                continue;
            }else {
                bands.add(actual);
            }
        }
        return bands.size();
    }
    private List<MembersOfSpecifiedCountry> countMembersPerCountry(List<Member> allMembers){
        List<MembersOfSpecifiedCountry> result = initToZero();

        for(int i = 0; i < result.size(); i++){
            String actualCountry= result.get(i).getCountry();
            int numberOfMembersFromCountry = countThatCountry(actualCountry,allMembers);
            result.get(i).setNumberFromThisCountry(numberOfMembersFromCountry);
        }
        return result;
    }
    private int countThatCountry(String countryToCount,List<Member> allMember){
        int count = 0;
        for (Member m : allMember){
            if(m.getCountry().equalsIgnoreCase(countryToCount))
                count++;
        }
        return count;
    }
    private List<MembersOfSpecifiedCountry> initToZero(){
        List<MembersOfSpecifiedCountry> zero = new ArrayList<>();
        //CANADA,FINNLAND,GERMANY,HUNGARY,NORWAY,SWEDEN,UK,USA
        String[] countries = registeredCountries();

        for (int i = 0; i < countries.length;i++){
            zero.add(new MembersOfSpecifiedCountry(countries[i],0));
        }

        return zero;
    }
    private String[] registeredCountries(){
        return new String[]{"Canada","Finnland","Germany","Hungary","Norway","Sweden","UK","USA"};
    }
}
