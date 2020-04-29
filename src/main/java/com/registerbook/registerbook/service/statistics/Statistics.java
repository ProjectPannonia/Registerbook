package com.registerbook.registerbook.service.statistics;

import com.registerbook.registerbook.model.Member;
import java.util.ArrayList;
import java.util.List;

public class Statistics {

    public int[] getStatitstics(List<Member> allMembers){
        int[] result = new int[2];
        // The first element is the number of registered members in database
        result[0] = allMembers.size();
        // The secon element is the number of bands in the database
        result[1] = numberOfBandsInTheDatabase(allMembers);

        return result;
    }
    private int numberOfBandsInTheDatabase(List<Member> allMembers){
        int numberOfBands = createAListOfBands(allMembers);
        return numberOfBands;
    }
    private int createAListOfBands(List<Member> allMembers){
        String[] result;
        List<String> filtered = new ArrayList<>();
        for (int i = 0; i < allMembers.size(); i++){
            String actual = allMembers.get(i).getBand();
            if(!filtered.contains(actual)) filtered.add(actual);
        }
        result = new String[filtered.size()];
        return result.length;
    }
}
