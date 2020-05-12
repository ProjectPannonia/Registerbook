package com.registerbook.registerbook.service.statistics.specialObjectsForStatistics;

import com.registerbook.registerbook.model.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statistics {

    public StatisticData getAdvancedStatistics(int numberOfMembers,int numberOfRegisteredBands, List<String> countries, List<Integer> memberPerCountry){

        StatisticData result = makePairs(numberOfMembers,numberOfRegisteredBands,countries,memberPerCountry);
        return result;
    }
    private StatisticData makePairs(int numberOfMembers,int numberOfRegisteredBands,List<String> countries,List<Integer> memberPerCountry){
        List<MembersPerCountry> pairs = new ArrayList<>();

        for(int i = 0; i < countries.size(); i++)
            pairs.add(new MembersPerCountry(countries.get(i),memberPerCountry.get(i)));

        return createStatistic(numberOfMembers,numberOfRegisteredBands,pairs);
    }

    private StatisticData createStatistic(int numberOfMembers,int numberOfRegisteredBands,List<MembersPerCountry> pairs) {
        StatisticData stat = new StatisticData();
        stat.setRegisteredMembers(numberOfMembers);
        stat.setNumberOfBands(numberOfRegisteredBands);
        //CANADA,FINNLAND,GERMANY,HUNGARY,NORWAY,SWEDEN,UK,USA
        stat.setCanadianBands(pairs.get(0).number);
        stat.setFinnBands(pairs.get(1).number);
        stat.setGermanBands(pairs.get(2).number);
        stat.setHungarianBands(pairs.get(3).number);
        stat.setNorwayBands(pairs.get(4).number);
        stat.setSwedenBands(pairs.get(5).number);
        stat.setUkBands(pairs.get(6).number);
        stat.setUsaBands(pairs.get(7).number);

        return stat;
    }

    public StatisticData getStatitstics(List<Member> allMembers){
        StatisticData statistic;
        /*List<MembersOfSpecifiedCountry> numberOfMembersByCountry;

        numberOfMembersByCountry = numberOfMembersByCountry(allMembers);
        int registeredMembers = allMembers.size();
        int numberOfBands = countBands(allMembers);
        int canadian = numberOfMembersByCountry.get(0).getNumberFromThisCountry();
        int finn = numberOfMembersByCountry.get(1).getNumberFromThisCountry();
        int german = numberOfMembersByCountry.get(2).getNumberFromThisCountry();
        int hungarian = numberOfMembersByCountry.get(3).getNumberFromThisCountry();
        int norwegian = numberOfMembersByCountry.get(4).getNumberFromThisCountry();
        int sweden = numberOfMembersByCountry.get(5).getNumberFromThisCountry();
        int uk = numberOfMembersByCountry.get(6).getNumberFromThisCountry();
        int usa = numberOfMembersByCountry.get(7).getNumberFromThisCountry();
         */
        return null;
    }

    private int countBands(List<Member> allMembers){
        String[] result;
        List<String> filtered = new ArrayList<>();
        for (int i = 0; i < allMembers.size(); i++){
            String actual = allMembers.get(i).getBand();
            if(!filtered.contains(actual)) filtered.add(actual);
        }
        result = new String[filtered.size()];
        return result.length;
    }

    public List<MembersOfSpecifiedCountry> numberOfMembersByCountry(List<Member> allMembers){
        List<MembersOfSpecifiedCountry> result = new ArrayList<>();
        String[] registeredCountries = getRegisteredCountries(allMembers);

        Arrays.sort(registeredCountries);

        for(int i = 0; i < registeredCountries.length; i++){
            int n = numberOfMemberOfSpecificCountry(registeredCountries[i],allMembers);
            result.add(new MembersOfSpecifiedCountry(registeredCountries[i],n));
        }

        return result;
    }
    private int numberOfMemberOfSpecificCountry(String country,List<Member> registeredMembers){
        int result = 0;

        for (Member m : registeredMembers){
            if(m.getCountry().equalsIgnoreCase(country))
                result++;
        }

        return result;
    }
    private String[] getRegisteredCountries(List<Member> allMembers){
        List<String> allCountry = new ArrayList<>();
        String[] result;
        int count = 0;

        for (Member m : allMembers){
            String country = m.getCountry();
            if (!allCountry.contains(country)) allCountry.add(country);
        }

        result = new String[allCountry.size()];

        for (String s : allCountry){
            result[count] = s;
            count++;
        }

        return result;
    }
}