package com.registerbook.registerbook.service.statistics;

import com.registerbook.registerbook.model.Member;
import java.util.ArrayList;
import java.util.List;

public class Statistics {

    public int[] getStatitstics(List<Member> allMembers){
        int[] result = new int[2];
        // The first element is the number of registered members in database
        result[0] = allMembers.size();
        // The second element is the number of bands in the database
        result[1] = numberOfBands(allMembers);

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
        int count = 0;

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
