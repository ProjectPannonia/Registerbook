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

    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    public Member findByName(String name){
        List<Member> allMembers = memberJpaRepository.findAll();
        String[] separateTheName = separateName(name);
        Member result = null;

        for (int i = 0; i < allMembers.size(); i++){
            Member actual = allMembers.get(i);
            if(actual.getFirstName().equals(separateTheName[0]) && actual.getLastName().equals(separateTheName[1])){
                result = actual;
            }
        }

        return result;
    }

    public Member findByNameCreating(String firstName,String lastName){
        List<Member> allMembers = memberJpaRepository.findAll();
        Member result = null;

        for (int i = 0; i < allMembers.size(); i++){
            Member actual = allMembers.get(i);
            if(actual.getFirstName().equals(firstName) && actual.getLastName().equals(lastName)){
                result = actual;
            }
        }

        return result;
    }

    public Member findById(Long id) {
        List<Member> allMembers = memberJpaRepository.findAll();
        Member member = null;
        for (Member m : allMembers){
            if(m.getId() == id) member = m;
        }
        return member;
    }

    public void deleteById(Long id) {
        memberJpaRepository.deleteById(id);
    }
    public void delete(Member member){
        memberJpaRepository.delete(member);
    }

    /*Private methods*/
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
    // End of private methods
}
