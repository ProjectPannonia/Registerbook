package com.registerbook.registerbook.service.statistics;

import com.registerbook.registerbook.model.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statistics {

    public int[] getStatitstics(List<Member> allMembers){
        int[] result = new int[10];
        // The first element is the number of registered members in database
        result[0] = allMembers.size();
        // The second element is the number of bands in the database
        result[1] = numberOfBands(allMembers);
        //Canada,Finnland,Germany,Hungary,Norway,Sweden,UK,USA
        List<MembersOfSpecifiedCountry> numberOfMembersByCountry = numberOfMembersByCountry(allMembers);
        // Canada
        result[2] = numberOfMembersByCountry.get(0).getNumberFromThisCountry();
        // Finnland
        result[3] = numberOfMembersByCountry.get(1).getNumberFromThisCountry();
        // Germany
        result[4] = numberOfMembersByCountry.get(2).getNumberFromThisCountry();
        //Hungary
        result[5] = numberOfMembersByCountry.get(3).getNumberFromThisCountry();
        // Norway
        result[6] = numberOfMembersByCountry.get(4).getNumberFromThisCountry();
        // Sweden
        result[7] = numberOfMembersByCountry.get(5).getNumberFromThisCountry();
        // UK
        result[8] = numberOfMembersByCountry.get(6).getNumberFromThisCountry();
        // USA
        result[9] = numberOfMembersByCountry.get(7).getNumberFromThisCountry();

        return result;
    }
    private int numberOfBands(List<Member> allMembers){
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
