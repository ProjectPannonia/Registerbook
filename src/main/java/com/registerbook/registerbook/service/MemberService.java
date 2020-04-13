package com.registerbook.registerbook.service;

import com.registerbook.registerbook.model.Member;
import com.registerbook.registerbook.repository.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    public List<Member> getAllMember(){
        List<Member> allMember = memberJpaRepository.findAll();
        return allMember;
    }

    public List<Member> getBandMembers(String bandName){
        List<Member> allMember = memberJpaRepository.findAll();
        List<Member> bandMembers = new ArrayList<>();
        for (Member m : allMember) {
            if(m.getBand().equals(bandName)) bandMembers.add(m);
        }
        return bandMembers;
    }

    public List<Member> getMembersByName(String name){
        List<Member> result = nameSearchResult(name);
        return result;
    }
    // getMembersByname assistant functions
    private List<Member> nameSearchResult(String name){
        List<Member> allMembers = memberJpaRepository.findAll();
        List<Member> result = new ArrayList<>();
        String[] separatedName = separateName(name);
        for (Member m : allMembers){
            if(m.getFirstName().equals(separatedName[0]) && m.getLastName().equals(separatedName[1])){
                result.add(m);
            }
        }
        return result;
    }
    private String[] separateName(String name){
        char[] nameCharacters = name.toCharArray();
        String[] result = new String[2];
        boolean isItSpace = false;
        String firstName = "";
        String lastNameProt = "";
        String lastName = "";
        for(int i = 0; i < nameCharacters.length;i++){
            if(Character.toString(nameCharacters[i]).equals("|")) isItSpace = true;
            if(!isItSpace) firstName += nameCharacters[i];
            else lastNameProt += nameCharacters[i];
        }
        lastName = lastNameProt.substring(1);
        result[0] = firstName;
        result[1] = lastName;

        return result;
    }
}
