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
        List<Member> allMembers;
        List<Member> result = new ArrayList<>();
        char[] nameCharacters = name.toCharArray();
        boolean isItSpace = false;
        String firstName = "";
        String lastName = "";

        for (int i = 0; i < nameCharacters.length; i++){
            if(Character.toString(nameCharacters[i]).equals("|")) {
                isItSpace = true;
            }
            if(!isItSpace) firstName += nameCharacters[i];
            else lastName += nameCharacters[i];
        }
        String lastNameF = lastName.substring(1);
        allMembers = memberJpaRepository.findAll();

        for (Member m : allMembers){
            if(m.getFirstName().equals(firstName) && m.getLastName().equals(lastNameF)){
                result.add(m);
            }
        }

        return result;
    }
}
