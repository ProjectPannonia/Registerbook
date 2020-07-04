package com.registerbook.registerbook.service.member.fileOperation.fileReader;

import com.registerbook.registerbook.model.entities.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MembersFileReader {
    private static int id = 0;

    public static List<Member> readMembersFromFile(String path){
        List<Member> loadedFromFile = new ArrayList<>();
        File membersTextFile = new File("D://LoadMembersToServer.txt");
        try {
            Scanner myScanner = new Scanner(membersTextFile);
            while (myScanner.hasNextLine()){
                String memberData = myScanner.nextLine();
                loadedFromFile.add(createMemberFromALine(memberData));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadedFromFile;
    }

    private static Member createMemberFromALine(String memberLine){
        String[] memberElements = memberLine.split(",");

        Member member = new Member();

        member.setId(new Long(id));
        member.setName((String) memberElements[0].trim());
        member.setBand((String) memberElements[1].trim());
        member.setInstrument((String) memberElements[2].trim());
        member.setCountry((String) memberElements[3].trim());
        member.setAddress((String) memberElements[4].trim());
        member.setEmail((String) memberElements[5].trim());
        member.setYearOfBirth(Integer.valueOf(memberElements[6].trim()));
        id++;
        return member;
    }
}
